package net.handler;

import net.HandlerInterface;

public class UserOffLineHandler implements HandlerInterface {

    //处理参数，
    //根据这个参数进行不同的处理，
    //比如是意外离线，还是安全退出等
    private String handlerParameter;

    /**
     *
     * @param msg 传过来参数
     * @return 给服务器返回是否成功 的 参数
     */
    public String handler(String msg) {
        handlerParameter = msg;
        return null;
    }





}
