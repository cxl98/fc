package com.easyArch.fight;

import com.easyArch.entity.PlayerInfo;
import com.easyArch.entity.UserInfo;

public interface LoginService {

    /**
     * 登录
     * @param info
     * @return
     */
    UserInfo login(UserInfo info);

    /**
     * 加载玩家信息
     * @param userId 通过id加载数据
     * @return
     */
    PlayerInfo loadPlayerInfo(String userId);

    /**
     * 注册
     * @return
     */
    UserInfo regist(UserInfo info);

    /**
     * 加入服务器缓存
     */
    void serverRegist();



}
