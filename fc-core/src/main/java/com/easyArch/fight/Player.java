package com.easyArch.fight;

import lombok.Data;

import java.net.Socket;


@Data
public class Player {
    private String userId;
    private String userName;

    private Socket socket;

    private int level;

    private boolean status;    //到时候匹配机制时进行原子操作判断 线程处理的时候要加锁
                                //true表示空闲，可以调度此玩家，false不可调度，
            //之所以做这个在多线程处理的时候可能在匹配上的时候还没来得及将玩家移出队列，就又被另一个线程访问了


}
