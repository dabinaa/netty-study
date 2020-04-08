package com.dabin.netty.response;

import com.dabin.netty.command.Packet;
import lombok.Data;

import static com.dabin.netty.command.Command.LOGIN_RESPONSE;


@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
