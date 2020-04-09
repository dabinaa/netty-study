package com.dabin.netty.command;

/**
 * @ClassName:Command 指令
 * @author: dabin
 * @date: 2020/4/623:05
 */
public interface Command {
    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;
}
