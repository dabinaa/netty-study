package com.dabin.netty.response;

import com.dabin.netty.command.Packet;
import lombok.Data;

import static com.dabin.netty.command.Command.MESSAGE_RESPONSE;

/**
 * @ClassName:MessageResponsePacket
 * @author: dabin
 * @date: 2020/4/823:52
 */
@Data
public class MessageResponsePacket extends Packet {

    private String msg;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
