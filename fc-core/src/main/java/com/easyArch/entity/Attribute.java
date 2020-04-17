package com.easyArch.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Attribute implements Serializable {
    private String userId;
    private int hp;
    private int def;
    private int attack;
}
