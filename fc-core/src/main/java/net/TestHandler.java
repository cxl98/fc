package net;

public class TestHandler implements HandlerInterface{

    private static String head="TestHandler收到了信息:";

    public String handler(String msg) {

        /*
         *这里去处理这个msg
         */
        msg=head+msg;


        return msg;
    }
}
