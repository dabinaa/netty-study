package com.dabin.netty.byteBuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @ClassName:ByteBufTest
 * @author: dabin
 * @date: 2020/4/622:24
 */
public class ByteBufTest {

    public static void main(String[] args) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(9, 100);
        print("allocate ByteBuf(9,100)", byteBuf);

        byteBuf.writeBytes(new byte[]{1, 2, 3, 4});
        print("write(1,2.3.4)", byteBuf);
    }


    private static void print(String action, ByteBuf byteBuf) {

        System.out.println("after=========" + action + "==========");
        System.out.println("capacity" + byteBuf.capacity());
        System.out.println("maxCapacity" + byteBuf.maxCapacity());
        System.out.println("readerIndex" + byteBuf.readerIndex());
        System.out.println("readableBytes" + byteBuf.readableBytes());
        System.out.println("isReadable" + byteBuf.isReadable());
        System.out.println("writeIndex" + byteBuf.writerIndex());
        System.out.println("writableBytes" + byteBuf.writableBytes());
        System.out.println("isWritable" + byteBuf.isWritable());
        System.out.println("maxWritableBytes" + byteBuf.maxWritableBytes());
        System.out.println("");
    }
}
