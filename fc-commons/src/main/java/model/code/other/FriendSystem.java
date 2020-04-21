package model.code.other;



import model.PlayerInfo;

import java.util.List;

public class FriendSystem {

    private String userId;

    private List<String> friendsId;


    //这个通过遍历好友列表，去服务器在线用户的hash找
    private List<PlayerInfo> onlineId;


}
