package com.easyarch.service;


import com.easyarch.entity.GroupMsg;

public interface GroupService {

    /**
     *
     * @param group 创建组的信息
     * @return 是否创建成功
     */
    boolean createGroup(GroupMsg group);

    //解散组
    boolean destroyGroup(GroupMsg groupMsg);

    //添加管理员
    boolean addManager(String groupId,String userId);


    //退出该组
    boolean quitGroup();


}
