package com.easyArch.service.Imp;

import com.easyArch.service.MatchMethod;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class MatchMethodImp implements MatchMethod {

    //范围数组
    private static Map<Integer, LinkedBlockingQueue<String>> map = new ConcurrentHashMap<>();
    //加一个监控队列中对象的信息
    //private static ExecutorService listen


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
