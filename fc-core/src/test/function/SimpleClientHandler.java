package function;

import com.easyarch.model.Message;
import com.easyarch.model.Operation;
import com.easyarch.model.code.Action;
import com.easyarch.model.code.CODE;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SimpleClientHandler extends SimpleChannelInboundHandler<Message> {



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {

//        TestMatch.returnCode = msg.getMsgCode();
        int code = msg.getMsgCode();
        if(code == CODE.LOGIN){
            TestLogin.player = msg.getObj().toString();
        }else if(code == CODE.FIGHT){

            Operation op = (Operation)msg.getObj();
            if(op.getAction()== Action.END){
                System.out.println("玩家胜利");
                TestFight.robot = null;
            }else if(op.getAction() == Action.INVALID){
                System.out.println("Invalid!");
                return;
            }else{
                TestFight.robot = op.getRobot();
            }
            TestFight.attr = op.getAttribute();
        }

        System.out.println("收到的返回信息:"+code+"收到服务器的数据:"+msg.getObj());

    }
}
