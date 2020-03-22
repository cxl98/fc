package net;


/**
 * 这个接口用于做handler接收到的msg做扩展，分析啥的
 */
public interface HandlerInterface {

    public String handler(String msg);

}
