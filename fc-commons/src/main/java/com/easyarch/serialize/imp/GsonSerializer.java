package com.easyarch.serialize.imp;


import com.google.gson.Gson;
import com.easyarch.serialize.Serializer;

public class GsonSerializer extends Serializer {

    private Gson gson;
    @SuppressWarnings("unchecked")
    @Override
    public <T> byte[] serializer(T obj) {
        if (null==obj){
            String s = gson.toJson("ERROR");
            return s.getBytes();
        }
        String s = gson.toJson(obj);
        System.out.println("obj"+s);
        return s.getBytes();
    }

    @Override
    public <T> Object deserializer(byte[] bytes, Class<T> clazz) {
        T t = gson.fromJson(new String(bytes), clazz);
        System.out.println("xxx"+t);
        return t;
    }
}
