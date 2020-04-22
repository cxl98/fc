package function;

import com.easyarch.model.Attribute;
import com.easyarch.model.Message;
import com.easyarch.model.Operation;
import com.easyarch.model.Robot;
import com.easyarch.model.code.Action;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestFight {

    private static SendMessageImp imp = new SendMessageImp();

    static Operation operation ;
    //返回的robot属性
    static Robot robot;
    //返回的玩家属性
    static Attribute attr;

    private static ExecutorService executorService  = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {

        NettyClient client = new NettyClient();

        Message message1 = imp.login("184500237","123456");
        client.sendMessage(message1);

        Message message = imp.fightBegin();
        client.sendMessage(message);


        try {
            Thread.sleep(2000);
            message = imp.fight(Action.ATTACK,robot,attr);
            client.sendMessage(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            Thread.sleep(2000);
            message = imp.fight(Action.ATTACK,robot,attr);
            client.sendMessage(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(2000);
            message = imp.fight(Action.BUFF,robot,attr);
            client.sendMessage(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
