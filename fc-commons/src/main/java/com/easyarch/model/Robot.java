package com.easyarch.model;


import lombok.Data;

/**
 * 创建机器人类
 */

@Data
public class Robot {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}

