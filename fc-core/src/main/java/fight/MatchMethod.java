package fight;

import java.util.Map;

public interface MatchMethod {


    /**
     * 开始匹配
     * @param p
     * @return
     */
    int match(Player p);

    /**
     * 加入匹配队列
     * @param p
     * @return
     */
    int add(Player p);

    /**
     * 取消匹配
     */
    void cancel();


    /**
     * 匹配成功处理
     * @return
     */
    Map<Player,Player> success();
}
