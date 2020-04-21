package com.easyArch.model.other;



import com.easyArch.model.PlayerInfo;
import lombok.Data;

import java.util.List;

@Data
public class FriendSystem {

    private String userId;

    private List<String> friendsId;


    //这个通过遍历好友列表，去服务器在线用户的hash找
    private List<PlayerInfo> onlineId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getFriendsId() {
        return friendsId;
    }

    public void setFriendsId(List<String> friendsId) {
        this.friendsId = friendsId;
    }

    public List<PlayerInfo> getOnlineId() {
        return onlineId;
    }

    public void setOnlineId(List<PlayerInfo> onlineId) {
        this.onlineId = onlineId;
    }
}
