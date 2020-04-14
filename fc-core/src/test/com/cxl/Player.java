package com.cxl;

public class Player {
    private int playerId;//玩家Id
    private int grade;//玩家等级
    private long startMatchTime;//开始匹配时间

    public Player(int playerId, int grade) {
        this.playerId = playerId;
        this.grade = grade;
        this.startMatchTime=System.currentTimeMillis();
    }

    public int getPlayerId() {
        return playerId;
    }


    public int getGrade() {
        return grade;
    }


    public long getStartMatchTime() {
        return startMatchTime;
    }

}
