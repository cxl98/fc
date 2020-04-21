package com.easyArch.model;


import lombok.Data;

@Data
public class Operation {

    private int level;
    private int action;
    private Attribute attribute;
    private String enemyId;
    private Robot robot;

    @Override
    public String toString(){
        return "Operation(level:" +level+
                ",action:" +action+
                ",attribute:" +attribute+
                ",enemyId:" +enemyId+
                ",Robot:" +robot+
                ")";
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getEnemyId() {
        return enemyId;
    }

    public void setEnemyId(String enemyId) {
        this.enemyId = enemyId;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }
}
