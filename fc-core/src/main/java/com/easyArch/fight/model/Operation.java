package com.easyArch.fight.model;

import com.easyArch.entity.Attribute;
import com.easyArch.utils.Robot;
import lombok.Data;

@Data
public class Operation {

    private int level;
    private int action;
    private Attribute attribute;
    private String enemyId;
    private Robot robot;

}
