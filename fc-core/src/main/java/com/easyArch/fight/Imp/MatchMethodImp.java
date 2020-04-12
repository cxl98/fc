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
    public void cancel() {

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
            if(map.get(rank).size()<=1){
//                Thread.sleep();
//                wait();
            }else{
                try {
                    String enemyId = map.get(rank).take();
                    //要让那个在等待的玩家停止等待
                    return enemyId;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
