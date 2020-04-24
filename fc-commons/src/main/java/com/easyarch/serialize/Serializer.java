package com.easyarch.serialize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.easyarch.serialize.imp.ProtoStuffSerializer;

public abstract class Serializer {
    private static Logger logger = LoggerFactory.getLogger(Serializer.class);

    public abstract <T> byte[] serializer(T obj);
    public abstract <T> Object deserializer(byte[] bytes,Class<T> clazz);

    public enum SerializerEnum{

        PROTOSTUFF(ProtoStuffSerializer.class);



        private Class<? extends Serializer> serializerClass;
        SerializerEnum(Class<? extends Serializer> serializerClass){

            this.serializerClass=serializerClass;
        }
        public Serializer getSerializer(){
            try {
                return serializerClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        public static SerializerEnum match(String name,SerializerEnum defaultSerializer){
            for (SerializerEnum item: SerializerEnum.values()) {
                if (item.name().equals(name)){
                    return item;
                }
            }
            return defaultSerializer;
        }
    }
}
