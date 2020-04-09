package com.easyArch.net;

import com.easyArch.fight.MonsterImp;
import com.easyArch.net.model.CODE;
import com.easyArch.net.model.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageHandler extends SimpleChannelInboundHandler<Message> {

    private MonsterImp monster = new MonsterImp();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        int code = msg.getMsgCode();
        Object obj = msg.getObj();

        if (code == CODE.FIGHT){
            obj = monster.getObj(obj);
            msg.setObj(obj);
            ctx.writeAndFlush(msg);
        }
    }
}
