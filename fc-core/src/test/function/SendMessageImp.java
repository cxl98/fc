package function;

import com.easyArch.model.*;
import com.easyArch.model.code.Action;
import com.easyArch.model.code.CODE;

/**
 * 这里写上需要调用的类
 *
 * 没有用单测。。。因为需要测流程啊，多线程啊，不太会灵活运用....
 */
public class SendMessageImp {


    public Message regist(){
        Message msg = new Message();
        msg.setMsgCode(CODE.REGIST);
        UserInfo us = new UserInfo();
        us.setUserId("111111");
        us.setUserPwd("123456");
        msg.setObj(us);
        return msg;
    }

    /**
     * 初次注册登录都会返回一个PlayerInfo信息
     */
    public Message login(){
        Message msg = new Message();
        msg.setMsgCode(CODE.LOGIN);
        UserInfo us = new UserInfo();
        us.setUserId("111111");
        us.setUserPwd("123456");
        msg.setObj(us);
        return msg;
    }

    /**
     * 初次注册登录都会返回一个PlayerInfo信息
     */
    public Message login(String username ,String pwd){
        Message msg = new Message();
        msg.setMsgCode(CODE.LOGIN);
        UserInfo us = new UserInfo();
        us.setUserId(username);
        us.setUserPwd(pwd);
        msg.setObj(us);
        return msg;
    }

    /**
     * 要改信息要把整个playerInfo都发过去...
     * 我还没有写 改单个属性
     * 因为客户端会有一个自己的playerInfo信息,改了之后直接发过去
     */
    public Message updatePlayer(){
        Message msg = new Message();
        msg.setMsgCode(CODE.UPDATE);
        PlayerInfo playerInfo = new PlayerInfo();
        playerInfo.setMoney(0);
        playerInfo.setWinCount(0);
        playerInfo.setClimbLevel(0);
        playerInfo.setRank(10);
        playerInfo.setUserName("test1");
        playerInfo.setFightCount(0);
        msg.setObj(playerInfo);

        return msg;
    }

    /**
     * 根据rank的匹配，要测的话要先注册用户,然后改一下rank分
     *
     */
    public Message match(){
        Message msg = new Message();
        msg.setMsgCode(CODE.MATCH);
        //带上自己的id就行
        msg.setObj("184500237");
        return msg;
    }

    /**
     * 打小怪初始化
     * @return
     */
    public Message fightBegin(){
        Message msg = new Message();
        msg.setMsgCode(CODE.FIGHT);
        msg.setObj(op);
        return msg;
    }

    /**
     *
     * @param action 下一步的操作
     * @param robot 目前的机器人血量(返回给前端显示，再次传输是为了和后端做对比,看玩家有没有开挂,但是这个判断还没做...)
     * @param attr 变化后的玩家属性
     * @return 消息
     */
    public Message fight(int action, Robot robot, Attribute attr){
        Message msg = new Message();
        msg.setMsgCode(CODE.FIGHT);
        op.setAction(action);
        op.setRobot(robot);
        op.setAttribute(attr);
        msg.setObj(op);
        return msg;
    }

    private static Attribute testA ;
    private static Operation op;

    static {
        testA = new Attribute();
        testA.setAttack(50);
        testA.setHp(200);
        testA.setDef(20);
        testA.setUserId("184500237");

        op = new Operation();
        op.setLevel(5);
        op.setAction(Action.START);
        op.setAttribute(testA);


    }

}
