package com.easyArch.net;

import com.easyArch.entity.PlayerInfo;
import com.easyArch.fight.Imp.MatchMethodImp;
import com.easyArch.fight.MonsterImp;
import com.easyArch.net.model.CODE;
import com.easyArch.net.model.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class MessageHandler extends SimpleChannelInboundHandler<Message> {

    @Autowired
    private MonsterImp monster ;

    @Autowired
    private MatchMethodImp match ;

    public static Map<String, ChannelId> userMap = new ConcurrentHashMap<>();

    //服务器redis缓存一份玩家基本信息
    public static Map<String, PlayerInfo> playerInfoMap = new ConcurrentHashMap<>();

    public static ExecutorService pool = Executors.newFixedThreadPool(20);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        int code = msg.getMsgCode();
        Object obj = msg.getObj();
        //打机器人
        if (code == CODE.FIGHT){
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    msg.setObj(obj);
                    ctx.writeAndFlush(msg);
                }
            });
        }


        if(code == CODE.MATCH){
            String self = (String)obj;
            int rank = playerInfoMap.get(self).getRank();

            pool.execute(new Runnable() {
                @Override
                public void run() {
                    String enemy = match.match(rank,self);
                    //如果需要线程等待，如何处理?
                    if(null==enemy||self.equals(enemy)){
                        msg.setMsgCode(CODE.MATCH_FAIL);
                        ctx.writeAndFlush(msg);
                    }else{
                        msg.setObj(enemy);
                        msg.setMsgCode(CODE.ENEMY);
                        //告知你的敌人id(换成name)
                        ctx.writeAndFlush(msg);
                    }
                }
            });
        }
    }



}
