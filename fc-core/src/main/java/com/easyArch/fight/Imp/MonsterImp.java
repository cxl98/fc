package com.easyArch.fight.Imp;


import com.easyArch.entity.Attribute;
import com.easyArch.fight.model.Action;
import com.easyArch.fight.model.BUFF;
import com.easyArch.fight.model.Operation;
import com.easyArch.utils.Robot;
import org.springframework.stereotype.Service;

@Service
public class MonsterImp {
    private Robot robot = null;
    private int level ;
    private int react ;


    public Object getObj(Object o){
        Operation op = (Operation)o;
        level = op.getLevel();
        react = op.getAction();
        if(Action.START==react&&null==robot){
            setRobot(level);
            op.setRobot(robot);
        }else if(robot!=null){
            Attribute attr = op.getAttribute();
            int action = op.getAction();

            op.setAttribute(fightRound(attr, action));
            op.setRobot(robot);
            op.setAction(react);
        }else{
            System.out.println("Invalid!");
            op.setAction(Action.INVALID);
        }
        //返回处理后的操作
        return op;
    }

    public Robot setRobot(){
        this.robot = new Robot();
        return robot;
    }

    private void setRobot(int level){
        this.robot = new Robot(level);
    }

    private Attribute fightRound(Attribute player, int action){
        if(robot==null){
            return player;
        }else{
            int pdf = player.getDef();
            int pat = player.getAttack();
            int php = player.getHp();
            if(action == Action.ATTACK){
                if(robot.getAttack()<=pdf){
                    //如果防御大于机器人的攻击，则不受伤害
                    react = Action.END;
                    return player;
                }else{
                    int damage = robot.getAttack()-pdf;
                    php -= damage;
                    if(php<=0){
                        //玩家失败
                        react = Action.DEATH;
                        robot = new Robot(level);
                        return player;
                    }

                    int rhp = robot.getHp()-pat;
                    if(rhp<=0){
                        //结束
                        react = Action.END;
                        robot = null;
                        return player;
                    }else{
//                        react=Action.ATTACK;
                        robot.setHp(rhp);
                        player.setHp(php);
                        return player;
                    }
                }
            }

            if(action==Action.BUFF){
                //简单做了个+攻击力的buff....
                pat += BUFF.BUFF_ADD_LITTLE;
                player.setAttack(pat);
                return player;
            }
        }

        react = Action.INVALID;
        return player;
    }
}
