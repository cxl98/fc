package com.easyarch.model.other;



import lombok.Data;

import java.io.Serializable;

@Data
public class FightRecord implements Serializable {
    /*
    可以用来返回战斗数据，
    还可以记录在数据库一份战斗记录
     */
    private String id;      //标明时间  开始时间
    private String userA;   //战斗发起者
    private String userB;   //战斗接收者
    private String rounds;  //回合战斗情况 0101010 表示玩家A的战斗情况是 输 赢 输 赢 输 赢 输，若7回合之前就结束战斗则后面补0

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserA() {
        return userA;
    }

    public void setUserA(String userA) {
        this.userA = userA;
    }

    public String getUserB() {
        return userB;
    }

    public void setUserB(String userB) {
        this.userB = userB;
    }

    public String getRounds() {
        return rounds;
    }

    public void setRounds(String rounds) {
        this.rounds = rounds;
    }
}
