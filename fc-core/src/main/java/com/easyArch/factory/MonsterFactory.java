package com.easyArch.factory;

import com.easyArch.model.Attribute;
import com.easyArch.model.Message;
import com.easyArch.model.Operation;
import com.easyArch.model.Robot;
import com.easyArch.model.code.Action;
import com.easyArch.model.code.BUFF;
import org.springframework.stereotype.Component;


@Component
public class MonsterFactory extends MessageAbstractFactory {
    private Robot robot;
    private int level ;
    private int react ;

    @Override
    public Message handle(Message msg) {
        Operation op = (Operation)msg.getObj();
        level = op.getLevel();
        react = op.getAction();
        //start
        if(Action.START==react&&null==robot){
            this.robot = new Robot(level);
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
        msg.setObj(op);
        return msg;
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
