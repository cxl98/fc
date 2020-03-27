package com.example.cms.entity;

import lombok.Data;

@Data
public class Player {

    private String userId;
    private String userName;
    private int fightCount;
    private int winCount;
    private int money;
    private int climbLevel;


}
