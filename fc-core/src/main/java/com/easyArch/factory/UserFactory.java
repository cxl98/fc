package com.easyArch.factory;

import com.easyArch.dao.imp.UserDaoImp;
import com.easyArch.entity.PlayerInfo;
import com.easyArch.entity.UserInfo;
import com.easyArch.net.model.CODE;
import com.easyArch.net.model.Message;
import com.easyArch.utils.RedisUtil;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
    处理用户业务
 */
@Component
public class UserFactory extends MessageAbstractFactory{

    @Autowired
    private UserDaoImp dao ;

    public UserFactory(ChannelHandlerContext ctx) {
        super(ctx);
    }

    @Override
    public Message handle(Message msg) {
        int code = msg.getMsgCode();
        if(code == CODE.LOGIN){
            return handleLogin(ctx,msg);
        }else if(code == CODE.REGIST){
            return handleRegist(ctx,msg);
        }else if(code == CODE.UPDATE){
            return handleUpdate(msg);
        }else if(code == CODE.SAVE){
            return handleSave(msg);
        }else{
            msg.setObj("ERROR");
        }
        return msg;
    }


    private Message handleRegist(ChannelHandlerContext ctx, Message msg){
        UserInfo us = (UserInfo) msg.getObj();
        if(regist(us)){
            //初始化玩家信息
            playerInit(us.getUserId());
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
        if(null!=login(us)){
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
                player = getPlayer(userId);
            }
            userMap.put(userId,ctx.channel().id());
//            msg.setMsgCode(CODE.SUCCESS);
            msg.setObj(player);
        }else{
            msg.setObj("登录失败,用户名或密码错误");
        }
        return msg;
    }

    private String login(UserInfo user) {
        System.out.println(user.getUserId());
        if(isUser(user.getUserId())){
            UserInfo userInfo = dao.searchUserByUserInfo(user);
            return userInfo.getUserId();
        }
        return null;
    }

    private boolean regist(UserInfo user) {
        if (!isUser(user.getUserId())){
            return dao.insertUser(user) == 1;
        }
        return false;
    }

    private boolean isUser(String id){
        return dao.searchById(id) != 0;
    }


    private void playerInit(String id){

        PlayerInfo player = new PlayerInfo();
        player.setUserId(id);
        player.setUserName("test");
        player.setRank(10);
        //数据库初始化玩家信息
        if(0!=dao.insertPlayer(player)){
            //redis缓存一份
            RedisUtil.updatePlayer(player);
        }

    }

    private boolean updatePlayer(PlayerInfo player){
        return dao.updatePlayer(player) != 0;
    }

    private PlayerInfo getPlayer(String userId){
        return dao.getPlayer(userId);
    }

    private Message handleUpdate(Message msg){
        PlayerInfo player = (PlayerInfo)msg.getObj();
//
        if(!RedisUtil.updatePlayer(player)){
            msg.setObj("更新失败");
        }
        return msg;
    }

    private Message handleSave(Message msg){
        Object obj = msg.getObj();
        String userId = (String) obj;
        PlayerInfo playerInfo = RedisUtil.getPlayer(userId);
        if(updatePlayer(playerInfo)){
            msg.setMsgCode(CODE.SUCCESS);
            msg.setObj("保存成功！");
            return msg;
        }
        msg.setMsgCode(CODE.ERROR);
        msg.setObj("保存失败");
        return msg;
    }

}
