package com.easyArch.factory;

import com.easyArch.factory.model.Operation;
import com.easyArch.net.MessageHandler;
import com.easyArch.net.model.CODE;
import com.easyArch.net.model.Message;
import com.easyArch.utils.RedisUtil;

import io.netty.channel.ChannelId;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class MatchFactory extends MessageAbstractFactory implements MatchMethod {

    //范围数组
    private static Map<Integer, LinkedBlockingQueue<String>> map = new ConcurrentHashMap<>();
    //加一个监控队列中对象的信息
    //private static ExecutorService listen

    @Override
    public Message handle(Message msg) {
        int code = msg.getMsgCode();
        if(code == CODE.MATCH){
            return handleMatch(msg);
        }else if(code == CODE.MATCH_FIGHT){
            return handleFight(msg);
        }else if(code == CODE.MATCH_FAIL){
            return handleFail(msg);
        }
        return null;
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

    private Message handleFail(Message msg){

        String self = (String)msg.getObj();

        int rank = getRank(self);

        try{
            //如果取消有异常
            cancel(rank,self);
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
    private int getRank(String self){
        String sRank = RedisUtil.getConnection().hget(self,RedisUtil.RANK);
        return Integer.valueOf(sRank);
    }

    private Message handleMatch(Message msg){
        System.out.println("MATCH");
        String self = (String)(msg.getObj());

        //获取隐藏分
        int rank = getRank(self);
        System.out.println("MATCH: RANK: "+rank);

        //等于只找一次，若没有结果则会停留在等待队列中
        String enemy = match(rank,self);

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
        }
        return msg;
    }


    @Override
    public void cancel(int rank, String userId) {
        System.out.println("玩家 "+userId+"离开匹配队列");
        map.get(rank).remove(userId);
    }

    @Override
    public void cancel(String userId) {

    }


    private boolean add(int rank, String userId) {
        if(map.containsKey(rank)){
            map.get(rank).add(userId);
        }else{
            LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
            queue.add(userId);
            map.put(rank,queue);
//            map.put(rank,new LinkedBlockingQueue<>());
        }
        System.out.println("玩家 "+userId+"加入匹配队列");
        return true;
    }

    @Override
    public String match(int rank,String userId) {
        boolean up = true;
        for(int i=rank,k = 1;k<=15;k++){
            if(map.containsKey(i)){
                try{
                    String enemyId;
                    enemyId = map.get(i).take();
                    System.out.println("e:"+enemyId);
                    return enemyId;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //中心扩散法
            if(up){
                i=i+k;
                up = false;
            }else{
                i=i-k;
                up = true;
            }
            System.out.println(i);
        }
        //没有匹配到则进入队列
        add(rank,userId);
        return null;
    }


}
