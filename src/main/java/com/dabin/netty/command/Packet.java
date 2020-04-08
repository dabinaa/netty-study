package com.dabin.netty.command;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @ClassName:Packet
 * @author: dabin
 * @date: 2020/4/622:53
 */
@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;


    /**
     * 获取指令的方法
     *
     * @return
     */
    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
