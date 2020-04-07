package com.dabin.netty.Serialize.Impl;


import com.alibaba.fastjson.JSON;
import com.dabin.netty.Serialize.Serializer;
import com.dabin.netty.Serialize.SerializerAlgorithm;

/**
 * @ClassName:JSONSerializer
 * @author: dabin
 * @date: 2020/4/623:12
 */
public class JSONSerializer implements Serializer {
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    public <T> T deserialize(Class<T> tClass, byte[] bytes) {
        return JSON.parseObject(bytes, tClass);
    }
}
