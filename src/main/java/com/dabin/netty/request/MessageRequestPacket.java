package com.dabin.netty.request;

import com.dabin.netty.command.Packet;
import lombok.Data;

import static com.dabin.netty.command.Command.MESSAGE_REQUEST;

/**
 * @ClassName:MessageRequestPacket
 * @author: dabin
 * @date: 2020/4/823:50
 */
@Data
public class MessageRequestPacket extends Packet {
    private String msg;


    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
