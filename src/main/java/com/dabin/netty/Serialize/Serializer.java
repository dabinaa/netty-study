package com.dabin.netty.Serialize;

import com.dabin.netty.Serialize.Impl.JSONSerializer;

/**
 * 序列化方法
 *
 * @ClassName:Serializer
 * @author: dabin
 * @date: 2020/4/623:07
 */
public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();


    /**
     * 序列化算法
     *
     * @return
     */
    byte getSerializerAlogrithm();


    /**
     * java对象转换为二进制
     *
     * @param object
     * @return
     */
    byte[] serialize(Object object);


    /**
     * 二进制装换为Java对象
     *
     * @param tClass
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> tClass, byte[] bytes);
}

