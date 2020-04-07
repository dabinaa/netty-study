package com.dabin.netty.command;

import lombok.Data;

import static com.dabin.netty.command.Command.LOGIN_REQUEST;

/**
 * @ClassName:LoginRequestPacket
 * @author: dabin
 * @date: 2020/4/623:03
 */

@Data
public class LoginRequestPacket extends Packet {

    private Integer userId;

    private String username;

    private String passWord;


    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
