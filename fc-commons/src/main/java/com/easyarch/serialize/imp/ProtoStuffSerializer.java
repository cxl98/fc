package com.easyarch.serialize.imp;


import com.easyarch.serialize.Serializer;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProtoStuffSerializer extends Serializer {
    private static final Objenesis OBJENESIS=new ObjenesisStd(true);

    private static Map<Class<?>, Schema<?>> cachedSchema=new ConcurrentHashMap<Class<?>, Schema<?>>();


    private static <T> Schema<T> getSchema(Class<T> clazz){
        @SuppressWarnings("unchecked")
        Schema<T> schema= (Schema<T>) cachedSchema.get(clazz);
        if (schema==null) {
           schema= RuntimeSchema.createFrom(clazz);
           if (schema!=null){
               cachedSchema.put(clazz,schema);
           }
        }
        return schema;
    }

    public <T> byte[] serializer(T obj) {
        @SuppressWarnings("unchecked")
        Class<T> clazz= (Class<T>) obj.getClass();
        LinkedBuffer buffer= LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

        try {
            Schema<T> schema=getSchema(clazz);
            return ProtostuffIOUtil.toByteArray(obj,schema,buffer);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally {
            buffer.clear();
        }
    }

    public <T> Object deserializer(byte[] bytes, Class<T> clazz) {
        try {
            T message=OBJENESIS.newInstance(clazz);
            Schema<T> schema=getSchema(clazz);
            ProtostuffIOUtil.mergeFrom(bytes,message,schema);
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
