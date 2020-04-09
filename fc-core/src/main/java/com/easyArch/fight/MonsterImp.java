package com.easyArch.fight;


import com.easyArch.entity.Attribute;
import com.easyArch.utils.Robot;

public class MonsterImp {
    private Robot robot ;

    public void setRobot(){
        this.robot = new Robot();
    }

    public void setRobot(int level){
        this.robot = new Robot(level);
    }

    public Attribute fightRound(Attribute player){
        if(robot==null){
            return player;
        }else{

        }
    }
}
