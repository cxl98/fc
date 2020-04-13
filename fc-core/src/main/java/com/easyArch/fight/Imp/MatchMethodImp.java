package com.easyArch.fight.Imp;

import com.easyArch.fight.MatchMethod;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class MatchMethodImp implements MatchMethod {

    //范围数组
    private static Map<Integer, LinkedBlockingQueue<String>> map = new ConcurrentHashMap<>();


    @Override
    public void cancel(int rank, String userId) {
        map.get(rank).remove(userId);
    }

    @Override
    public void cancel(String userId) {

    }

    @Override
    public boolean add(int rank, String userId) {
        if(map.containsKey(rank)){
            map.get(rank).add(userId);
        }else{
            map.put(rank,new LinkedBlockingQueue<>());
        }
        return true;
    }

    @Override
    public String match(int rank,String userId) {
        boolean isInQueue = false;
        isInQueue = add(rank,userId);
        if(isInQueue){
            //如果队列中没人或人不够
            if(map.get(rank).size()<1){
                return null;
            }else{
                try {

                    String enemyId = "";
                    //我想达到的目的
                    //多线程处理不能同时获取到同一个userId
                    synchronized (enemyId){
                         enemyId = map.get(rank).take();
                    }

                    return enemyId;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
