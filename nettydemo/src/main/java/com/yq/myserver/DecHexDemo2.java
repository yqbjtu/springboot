package com.yq.myserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.math.BigInteger;

/**
 * Created by yangqian on 2019/6/22.
 */
public class DecHexDemo2 {
    public static void main(String[] args) throws Exception {
        String str = "11";
        BigInteger bigInteger1 = new BigInteger(str,16);
        BigInteger bigInteger2 = new BigInteger(str,10);
        System.out.println( bigInteger1 + ", " + bigInteger2);
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeByte(4);
        byteBuf.writeByte(0xe8);
        //dec 1256  --hex 4e8
        DecHexDemo2 demo = new DecHexDemo2();
        demo.printDecHexConvert(byteBuf, "4e8", 1256);

        ByteBuf byteBuf2 = Unpooled.buffer();
        byteBuf2.writeByte(0);
        byteBuf2.writeByte(0xe8);
        //dec 232  --hex e8
        demo.printDecHexConvert(byteBuf2, "e8", 232);


        ByteBuf byteBuf3 = Unpooled.buffer();
        byteBuf3.writeByte(0);
        byteBuf3.writeByte(0x11);
        //dec 17  --hex 11
        demo.printDecHexConvert(byteBuf3, "0x11", 17);

        byteBuf3 = Unpooled.buffer();
        byteBuf3.writeByte(0);
        byteBuf3.writeByte(0x99);
        //dec 153  --hex 99
        demo.printDecHexConvert(byteBuf3, "ox99", 153);

        ByteBuf byteBuf4 = Unpooled.buffer();
        byteBuf4.writeByte(0);
        byteBuf4.writeByte(0xFF);
        //dec 295  --hex 127
        demo.printDecHexConvert(byteBuf4, "FF", 255);

    }

    private void printDecHexConvert(ByteBuf byteBuf, String hexStr, int decimalValue) {
        long frameLength = (long)byteBuf.getUnsignedShort(0);

        long low = (long)byteBuf.getUnsignedByte(0);
        long high = (long)byteBuf.getUnsignedByte(1);
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(String.format("%02x", low)).append(String.format("%02x",high));
        String str = strBuilder.toString();
        BigInteger bigInteger = new BigInteger(str,16);
        System.out.println("decimalVal:" + decimalValue + ", HexStr:" + hexStr +
                ", getStr:" + str + "---" + frameLength + "," + bigInteger);
    }
}
