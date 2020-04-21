package model;


import java.io.Serializable;

public class Attribute implements Serializable {
    private String userId;
    private int hp;
    private int def;
    private int attack;

    @Override
    public String toString(){
        return "Attribute(userId:" +userId+
                ",hp:" +hp+
                ",def:" +def+
                ",attack:"+attack+
                ")";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
