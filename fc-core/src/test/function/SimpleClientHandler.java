package function;

import com.easyArch.net.model.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SimpleClientHandler extends SimpleChannelInboundHandler<Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {

        TestMatch.returnCode = msg.getMsgCode();

        System.out.println("收到的返回信息:"+msg.getMsgCode()+"收到服务器的数据:"+msg.getObj());

    }
}
