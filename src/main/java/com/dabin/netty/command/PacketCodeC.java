package com.dabin.netty.command;

import com.alibaba.fastjson.serializer.JSONSerializer;

import com.dabin.netty.Serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static com.dabin.netty.command.Command.LOGIN_REQUEST;

/**
 * @ClassName:PacketCodeC
 * @author: dabin
 * @date: 2020/4/70:01
 */
public class PacketCodeC {

    private static final int MAGIC_NUMBER = 0X12345678;
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = (Serializer) new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {

        //创建byteBuf对象
        ByteBuf byteBuf = byteBufAllocator.DEFAULT.ioBuffer();
        //序列化byteBuf对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        //实际编码过程

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeByte(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }


    public Packet decode(ByteBuf byteBuf) {

        //跳过 magic number
        byteBuf.skipBytes(4);
        //跳过版本号
        byteBuf.skipBytes(1);
        //序列化算法表示
        byte serializeAlgorithm = byteBuf.readByte();

        //指令
        byte command = byteBuf.readByte();

        //获取数据包长度
        int length = byteBuf.readInt();


        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requsetType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requsetType != null && serializer != null) {
            return serializer.deserialize(requsetType, bytes);
        }
        return null;

    }


    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }
}
