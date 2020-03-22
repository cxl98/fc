package fight.Imp;

import fight.MatchMethod;
import fight.Player;

import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 匹配机制
 */
public class MatchMethodHandler implements MatchMethod {
    /**
     * 8个队列，7个是按等级分配的，先进先出，0代表机器人组，如果在7组中没匹配到则给个机器人，并返回匹配成功
     */
    public static Queue[] matchArray = new Queue[8];//0,1,2,3,4,5,6,7    0的话 匹配个机器人吧！数组下标当等级返回值了

    public int match(Player p) {
        return 0;
    }

    public int add(Player p) {
        return 0;
    }

    public void cancel() {

    }

    public Map<Player, Player> success() {
        return null;
    }
}
