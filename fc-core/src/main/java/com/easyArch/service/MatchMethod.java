package com.easyArch.service;

public interface MatchMethod  {

    /**
     * 取消匹配
     */
    void cancel(int rank,String userId);

    void cancel(String userId);


//    /**
//     *
//     * @param rank 根据rank分 进入队列
//     * @param userId 自己的id
//     * @return 是否加入成功
//     */
//    boolean add(int rank,String userId);

    /**
     *
     * @param userId
     * @param rank 根据rank分(范围) 寻找队列
     * @return 返回你匹配到的对手id
     */
    String match(int rank,String userId);





}
