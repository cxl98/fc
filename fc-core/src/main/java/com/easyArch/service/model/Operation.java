package com.easyArch.service.model;

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

    @Override
    public String toString(){
        return "Operation(level:" +level+
                ",action:" +action+
                ",attribute:" +attribute+
                ",enemyId:" +enemyId+
                ",Robot:" +robot+
                ")";
    }
}
