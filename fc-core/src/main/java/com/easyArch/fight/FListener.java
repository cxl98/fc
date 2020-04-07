package com.easyArch.fight;


/**
 * 这listener应该交给netty吧，我忘了咋写了，先这样写着
 */
public interface FListener {


    void init();

    void timeLoop();


    /**
     * 轮询时若未接收到包则反馈时在心跳中加上索要的内容
     * @return 发送心跳包
     */
//    Beat sendBeat();

}
