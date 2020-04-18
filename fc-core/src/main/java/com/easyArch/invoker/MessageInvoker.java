package com.easyArch.invoker;

import com.easyArch.entity.PlayerInfo;
import com.easyArch.entity.UserInfo;
import com.easyArch.service.Imp.MatchMethodImp;
import com.easyArch.service.Imp.UserServiceImp;
import com.easyArch.service.Imp.MonsterImp;
import com.easyArch.service.model.Operation;
import com.easyArch.net.MessageHandler;
import com.easyArch.net.model.CODE;
import com.easyArch.net.model.Message;
import com.easyArch.utils.RedisUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MessageInvoker {

    @Autowired
    private MonsterImp monster ;
    @Autowired
    private MatchMethodImp match ;
    @Autowired
    private UserServiceImp userService ;

    private Map<String, ChannelId> userMap = new ConcurrentHashMap<>();


    //服务器redis缓存一份玩家基本信息
    //public static Map<String, PlayerInfo> playerInfoMap = new ConcurrentHashMap<>();

    public Message handle(ChannelHandlerContext ctx, Message msg){
        int code = msg.getMsgCode();
        if(code == CODE.LOGIN){
            return handleLogin(ctx,msg);
        }else if(code == CODE.REGIST){
            return handleRegist(ctx,msg);
        }else if(code == CODE.UPDATE){
            return handleUpdate(msg);
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
        else if(code == CODE.SAVE){
            return handleSave(msg);
        }

        return null;
    }

    private Message handleRegist(ChannelHandlerContext ctx,Message msg){
        UserInfo us = (UserInfo) msg.getObj();
        if(userService.regist(us)){
            //初始化玩家信息
            userService.playerInit(us.getUserId());
            //先存MySQL---再存redis---登录(先访问redis---再访问数据库)
            return handleLogin(ctx,msg);
        }
        msg.setMsgCode(CODE.REGIST);
        msg.setObj("注册失败,此id已注册");
        return msg;
    }

    private Message handleLogin(ChannelHandlerContext ctx, Message msg){
        System.out.println("login----");
        UserInfo us = (UserInfo) msg.getObj();
        if(null!=userService.login(us)){
            String userId = us.getUserId();
            PlayerInfo player ;
                //如果在redis里
            if(RedisUtil.isContainsKey(userId)){
                System.out.println("redis");
                player = RedisUtil.getPlayer(userId);
            }
                //如果没在redis里
            else{
                System.out.println("MySQL");
                player = userService.getPlayer(userId);
            }
            userMap.put(userId,ctx.channel().id());
//            msg.setMsgCode(CODE.SUCCESS);
            msg.setObj(player);
        }else{
            msg.setMsgCode(CODE.LOGIN);
            msg.setObj("登录失败,用户名或密码错误");
        }
//        PlayerInfo player = (PlayerInfo)msg.getObj();
//        String userId = player.getUserId();
//        System.out.println("userId: "+userId);
//        userMap.put(userId,ctx.channel().id());
//        //模拟存储到服务器缓存(redis)
//        playerInfoMap.put(userId,player);
//        msg.setMsgCode(CODE.SUCCESS);
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

//        int rank = playerInfoMap.get(self).getRank();
        String sRank = RedisUtil.getConnection().hget(self,RedisUtil.RANK);
        int rank = Integer.valueOf(sRank);

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
//        int rank = playerInfoMap.get(self).getRank();
        String sRank = RedisUtil.getConnection().hget(self,RedisUtil.RANK);
//        if()
        int rank = Integer.valueOf(sRank);

        try{
            //如果取消有异常
            match.cancel(rank,self);
            msg.setMsgCode(CODE.SUCCESS);
            msg.setObj("取消匹配!");
        }catch (Exception e){
            //告诉客户端重新发送
            msg.setMsgCode(CODE.RETRY);
            msg.setObj(e.getMessage());
            return msg;
        }
        return msg;
    }

    private Message handleUpdate(Message msg){
        PlayerInfo player = (PlayerInfo)msg.getObj();
//        Map
        RedisUtil.updatePlayer(player);
        msg.setMsgCode(CODE.SUCCESS);
        return msg;
    }

    private Message handleSave(Message msg){
        Object obj = msg.getObj();
        String userId = (String) obj;
        PlayerInfo playerInfo = RedisUtil.getPlayer(userId);
        if(userService.updatePlayer(playerInfo)){
            msg.setMsgCode(CODE.SUCCESS);
            msg.setObj("保存成功！");
            return msg;
        }
        msg.setMsgCode(CODE.ERROR);
        msg.setObj("保存失败");
        return msg;
    }

}
