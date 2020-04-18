package com.easyArch.fight;

import com.easyArch.entity.UserInfo;

public interface UserService {

    UserInfo login(UserInfo user);

    boolean regist(UserInfo user);


}
