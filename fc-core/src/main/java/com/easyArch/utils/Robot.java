package com.easyArch.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 创建机器人类
 */
@Data
public class Robot{
    private String name;
    private int level = 0;  //1  2  3  4   5   6   7
    private int hp = 10;    //20 40 80 160 320 640 1280
    private int def = 0;    //10 20 30 40  50  60  70
    private int attack = 0; //10 20 40 80  120 200 300
    private boolean alive = true;

    public Robot(){

    }

    public Robot(int level){
        this.level = level;
        hp = 10 * (2^level);
        def = 10 * level;
        attack = 5 * level;
    }


}

