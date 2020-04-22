package com.easyarch.model;



import lombok.Data;

import java.io.Serializable;

@Data
public class PlayerInfo implements Serializable {
    private String userId;
    private String userName;
    private int fightCount;//战斗数
    private int winCount;  //胜场
    private int money;     //钱
    private int climbLevel;//闯关级别
    private int rank;      //战斗力

    /*
    未来还可能有的业务
     */
//    private


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getFightCount() {
        return fightCount;
    }

    public void setFightCount(int fightCount) {
        this.fightCount = fightCount;
    }

    public int getWinCount() {
        return winCount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getClimbLevel() {
        return climbLevel;
    }

    public void setClimbLevel(int climbLevel) {
        this.climbLevel = climbLevel;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
