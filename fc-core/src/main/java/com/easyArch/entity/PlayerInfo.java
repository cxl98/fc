package com.easyArch.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
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
}
