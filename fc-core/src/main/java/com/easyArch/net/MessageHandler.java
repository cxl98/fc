package com.easyArch.net;

import com.easyArch.entity.PlayerInfo;
import com.easyArch.fight.Imp.MatchMethodImp;
import com.easyArch.fight.MonsterImp;
import com.easyArch.fight.model.Operation;
import com.easyArch.net.model.CODE;
import com.easyArch.net.model.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageHandler extends SimpleChannelInboundHandler<Message> {

    private MonsterImp monster = new MonsterImp();

    private MatchMethodImp match = new MatchMethodImp();

    public static Map<String, ChannelId> userMap = new ConcurrentHashMap<>();

    //服务器redis缓存一份玩家基本信息
    public static Map<String, PlayerInfo> playerInfoMap = new ConcurrentHashMap<>();

    public static ExecutorService pool = Executors.newFixedThreadPool(20);

    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        int code = msg.getMsgCode();
        System.out.println("code:"+code);
        Object obj = msg.getObj();
        System.out.println("------reading------");

        if(code == CODE.LOGIN){
            System.out.println("login----");
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    PlayerInfo player = (PlayerInfo)obj;
                    String userId = player.getUserId();
                    System.out.println("userId: "+userId);
                    userMap.put(userId,ctx.channel().id());
                    //模拟存储到服务器缓存(redis)
                    playerInfoMap.put(userId,player);
                    msg.setMsgCode(CODE.SUCCESS);
                    ctx.writeAndFlush(msg);
                }
            });
        }

        //打机器人
        else if (code == CODE.FIGHT){
            Object mos = monster.getObj(obj);
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    msg.setObj(mos);
                    ctx.writeAndFlush(msg);
                }
            });
        }
        //匹配
        else if(code == CODE.MATCH){
            System.out.println("MATCH");
            String self = (String)obj;
            int rank = playerInfoMap.get(self).getRank();
            System.out.println("MATCH: RANK: "+rank);

            pool.execute(new Runnable() {
                @Override
                public void run() {
                    //等于只找一次，若没有结果则会停留在等待队列中
                    String enemy = match.match(rank,self);

                    if(null==enemy||self.equals(enemy)){
                        //告诉客户端等待
                        msg.setMsgCode(CODE.MATCH_WAIT);
                        ctx.writeAndFlush(msg);
                    }else{
                        //匹配到了
                        msg.setObj(enemy);
                        msg.setMsgCode(CODE.ENEMY);

                        //将你自己的id告诉对手
                        Message toEnemy = new Message();
                        toEnemy.setMsgCode(CODE.ENEMY);
                        toEnemy.setObj(self);
                        group.find(userMap.get(enemy)).writeAndFlush(toEnemy);
                        //把自己移出匹配队列
//                        match.cancel(rank,self);

                        //告诉自己你的对手id是啥(换成name)
                        ctx.writeAndFlush(msg);
                    }
                }
            });
        }
        //匹配后打架
        else if(code ==  CODE.MATCH_FIGHT){
            String enemy = ((Operation)obj).getEnemyId();
            ChannelId enemyId = userMap.get(enemy);
            //将你的操作直接发给敌人的客户端去处理
            group.find(enemyId).writeAndFlush(obj);
        }

        //如果客户端返回了匹配失败的代码
        else if(code == CODE.MATCH_FAIL){
            String self = (String)obj;
            int rank = playerInfoMap.get(self).getRank();
            match.cancel(rank,self);
        }


    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("-------Regist-------");
        group.add(ctx.channel());
        super.channelRegistered(ctx);
    }
}
