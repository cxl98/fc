package com.cxl;

import java.util.*;
import java.util.concurrent.*;

public class Mecth {
    /**
     * 线程池
     */
    private static ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    private static ExecutorService service1=Executors.newSingleThreadScheduledExecutor();


    /**
     * 需要匹配的玩家数
     */
    private static final int MATCH_PLAYER_COUNT = 1;

    /**
     * 玩家匹配的集合
     */
    private static ConcurrentMap<Integer, Player> concurrentMapPlayer = new ConcurrentHashMap<>();

    static {
        service.scheduleWithFixedDelay(() -> process(concurrentMapPlayer), 1, 1, TimeUnit.SECONDS);

    }

    /**
     * 初始话所有玩家.把他们加到concurrentMapPlayer中，方便进行玩家之间的匹配操作
     * @param playerId 玩家id
     * @param grade 等级
     */
    public  void addPlayer(int playerId, int grade) {
        Player player = new Player(playerId, grade);
        concurrentMapPlayer.put(playerId, player);
    }

    private static void process(ConcurrentMap<Integer, Player> concurrentMapPlayer) {
        long currentTime = System.currentTimeMillis();
        System.out.println("匹配开始" + currentTime);

        TreeMap<Integer, HashSet<Player>> map = new TreeMap<>();
        for (Player item : concurrentMapPlayer.values()) {
            //匹配超时时间为2分钟
            if (2 * 60 * 1000 < (System.currentTimeMillis() - item.getStartMatchTime())) {
                System.out.println(item.getPlayerId() + " 匹配超时");
                removePlayerById(item.getPlayerId());
                continue;
            }
            HashSet<Player> set = map.get(item.getGrade());
            if (null == set) {
                set = new HashSet<>();
                set.add(item);
                map.put(item.getGrade(), set);
            } else {
                set.add(item);
            }
        }

        for (HashSet<Player> itemSet : map.values()) {
            boolean continueMatch = true;

            while (continueMatch) {
                Player player = null;
                for (Player itemPlayer : itemSet) {
                    if (null == player) {
                        player = itemPlayer;
                    } else if (player.getStartMatchTime() > itemPlayer.getStartMatchTime()) {
                        player = itemPlayer;
                    }
                }
                if (null == player) {
                    break;
                }
                System.out.println(player.getPlayerId() + " 该等级上等待最久的玩家开始匹配：该玩家的等级为：　" + player.getGrade());

                long now = System.currentTimeMillis();
                int waitTime = (int) (now - player.getStartMatchTime() / 1000);
                System.out.println(player.getPlayerId() + " 等待的时间为：" + waitTime + "秒　　：   当前的时间为：　" + now);


                //按等待时间扩大匹配范围
                float load = 1.5f;
                int section = 15;

                double u = Math.pow(waitTime, load);
                u = Math.round(u);
                u = Math.min(u, section);

                int min = (player.getGrade() - u) < 0 ? 0 : (int) (player.getGrade() - u);
                int max = player.getGrade() + u > 60 ? 60 : (int) (player.getGrade() + u);

                System.out.println(player.getPlayerId() + "  本次搜索的等级范围是：[" + min + " ~~ " + max + "]");

                int middle = player.getGrade();
                List<Player> matchPlayer = new ArrayList<>();
                //从这个玩家的等级向向两边扩大匹配范围

                for (int searchGradeUp = middle, searchGradeDown = middle; searchGradeUp <= max || searchGradeDown >= min; searchGradeUp++, searchGradeDown--) {

                    HashSet<Player> thisGradePlayer;
                    thisGradePlayer = map.getOrDefault(searchGradeUp, new HashSet<>());
                    if (searchGradeDown != searchGradeUp && searchGradeDown > 0) {
                        thisGradePlayer.addAll(map.getOrDefault(searchGradeDown, new HashSet<>()));
                    }
                    if (!thisGradePlayer.isEmpty()) {
                        if (MATCH_PLAYER_COUNT > matchPlayer.size()) {
                            Iterator<Player> iterator = thisGradePlayer.iterator();
                            while (iterator.hasNext()) {
                                Player player1 = iterator.next();
                                //排除自己
                                if (player1.getPlayerId() != player.getPlayerId()) {
                                    if (MATCH_PLAYER_COUNT > matchPlayer.size()) {
                                        matchPlayer.add(player1);
                                        System.out.println(player.getPlayerId() + " 匹配到玩家" + player1.getPlayerId());
                                        iterator.remove();
                                        concurrentMapPlayer.remove(player.getPlayerId(),player);
                                        concurrentMapPlayer.remove(player1.getPlayerId(),player1);
                                    } else {
                                        break;
                                    }
                                }
                            }
                        } else {
                            break;
                        }
                    }

                }

                if (MATCH_PLAYER_COUNT == matchPlayer.size()) {
                    System.out.println(player.getPlayerId() + " 匹配成功，且可以进行打架的操作了");
                    //将自己移除集合
                    itemSet.remove(player);
                    //匹配成功的处理
                    matchPlayer.add(player);
                    //匹配成功后的处理  。还没有写,打架的具体操作还在筹划中
                    //matchSuccessProcess(matchPlayer);

                } else {
                    //匹配失败的操作
                    continueMatch = false;
                    System.out.println(player.getPlayerId() + " 没有匹配到玩家，请重新匹配");
                    //把取出来的玩家重新放回集合中
                    for (Player returnPlayer : matchPlayer) {
                        HashSet<Player> players = map.get(player.getGrade());
                        players.add(returnPlayer);
                    }
                }

            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("执行匹配玩家结束,结束时间为：%d耗时%dms", endTime, endTime - currentTime));
    }

    /**
     * 从玩家的集合中把玩家移除
     *
     * @param playerId 　玩家的ｉｄ
     */
    private static void removePlayerById(int playerId) {
        concurrentMapPlayer.remove(playerId);
    }


}
