package com.easyArch.net;

import com.easyArch.entity.PlayerInfo;
import com.easyArch.fight.Imp.LoginServiceImp;
import com.easyArch.fight.Imp.MatchMethodImp;
import com.easyArch.fight.MonsterImp;
import com.easyArch.fight.model.Operation;
import com.easyArch.net.model.CODE;
import com.easyArch.net.model.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MessageInvoker {

    private MonsterImp monster = new MonsterImp();

    private MatchMethodImp match = new MatchMethodImp();

    private LoginServiceImp login = new LoginServiceImp();

    public static Map<String, ChannelId> userMap = new ConcurrentHashMap<>();

    //服务器redis缓存一份玩家基本信息
    public static Map<String, PlayerInfo> playerInfoMap = new ConcurrentHashMap<>();


    public Message handle(ChannelHandlerContext ctx,Message msg){
        int code = msg.getMsgCode();
        if(code == CODE.LOGIN){
            return handleLogin(ctx,msg);
        }
        //打机器人
        else if (code == CODE.FIGHT){
            return handleRobot(msg);
        }
        //匹配
        else if(code == CODE.MATCH){
            return handleMatch(msg);
        }
        //匹配后打架
        else if(code ==  CODE.MATCH_FIGHT){
            return handleFight(msg);
        }

        //如果客户端返回了匹配失败的代码
        else if(code == CODE.MATCH_FAIL){
            return handleFail(msg);
        }
        return null;
    }

    private Message handleLogin(ChannelHandlerContext ctx, Message msg){
        System.out.println("login----");

        PlayerInfo player = (PlayerInfo)msg.getObj();
        String userId = player.getUserId();
        System.out.println("userId: "+userId);
        userMap.put(userId,ctx.channel().id());
        //模拟存储到服务器缓存(redis)
        playerInfoMap.put(userId,player);
        msg.setMsgCode(CODE.SUCCESS);
        return msg;
    }

    private Message handleFight(Message msg){
        Object obj = msg.getObj();
        String enemy = ((Operation)obj).getEnemyId();
        ChannelId enemyId = userMap.get(enemy);
        //将你的操作直接发给敌人的客户端去处理
        MessageHandler.group.find(enemyId).writeAndFlush(msg);
        Message m = new Message();
        m.setMsgCode(CODE.SUCCESS);
        m.setObj("SUCCESS");
        return m;
    }

    private Message handleMatch(Message msg){

        System.out.println("MATCH");
        String self = (String)(msg.getObj());

        int rank = playerInfoMap.get(self).getRank();
        System.out.println("MATCH: RANK: "+rank);

        //等于只找一次，若没有结果则会停留在等待队列中
        String enemy = match.match(rank,self);

        if(null==enemy||self.equals(enemy)){
            //告诉客户端等待
            msg.setMsgCode(CODE.MATCH_WAIT);

        }else{
            //匹配到了
            msg.setObj(enemy);
            msg.setMsgCode(CODE.ENEMY);

            //将你自己的id告诉对手
            Message toEnemy = new Message();
            toEnemy.setMsgCode(CODE.ENEMY);
            toEnemy.setObj(self);
            MessageHandler.group.find(userMap.get(enemy)).writeAndFlush(toEnemy);
            //把自己移出匹配队列
            //match.cancel(rank,self);

            //告诉自己你的对手id是啥(换成name)
        }
        return msg;
    }


    private Message handleRobot(Message msg){
        Object gObj = msg.getObj();
        Object mos = monster.getObj(gObj);
        msg.setObj(mos);
        return msg;
    }

    private Message handleFail(Message msg){
        Object obj = msg.getObj();
        String self = (String)obj;
        int rank = playerInfoMap.get(self).getRank();
        match.cancel(rank,self);
        Message m = new Message();
        m.setMsgCode(CODE.SUCCESS);
        m.setObj("SUCCESS");
        return m;
    }

}
