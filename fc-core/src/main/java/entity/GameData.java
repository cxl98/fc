package entity;

import lombok.Data;

@Data
public class GameData {
    private String userId;
    private String userName;
    private int fightCount;//战斗数
    private int winCount;  //胜场
    private int money;     //钱
    private int climbLevel;//闯关级别

    /*
    未来还可能有的业务
     */
//    private
}
