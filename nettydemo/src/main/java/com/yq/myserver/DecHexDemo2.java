package com.yq.myserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

/**
 * Created by yangqian on 2019/6/22.
 */
public class DecHexDemo2 {
    public static void main(String[] args) throws Exception {
        String str = "11";
        //String str = "0e";
        Integer bigInteger1 =  Integer.parseInt(str,16);
        BigInteger bigInteger2 = new BigInteger(str,10);
        System.out.println( bigInteger1 + ", " + bigInteger2);
        //byte[] bytes1 = new byte[] {0x0a, 0x60};
        //凡是以正确的0x方式写入的16进制，就会正确读取出来getUnsignedShort, 不需要转换
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeByte(4);
        byteBuf.writeByte(0xe8);

        String hexDump = ByteBufUtil.hexDump(byteBuf);
        System.out.println( byteBuf + ", " + hexDump);

        byteBuf = Unpooled.buffer();
        //byteBuf.writeByte(4);
        //byteBuf.writeByte(0xe8);
        //dec 1256  --hex 4e8
        DecHexDemo2 demo = new DecHexDemo2();
        demo.printDecHexConvert(byteBuf, "4e8", 1256);

        byteBuf = Unpooled.buffer();
        //byteBuf.writeByte(0x14); //必须写成0x14， 不能是14
        //byteBuf.writeByte(0xe8);
        //dec 5352  --hex 14e8
        demo = new DecHexDemo2();
        demo.printDecHexConvert(byteBuf, "14e8", 5352);

        ByteBuf byteBuf2 = Unpooled.buffer();
        //byteBuf2.writeByte(0);
        //byteBuf2.writeByte(0xe8);
        //dec 232  --hex e8
        demo.printDecHexConvert(byteBuf2, "e8", 232);


        ByteBuf byteBuf3 = Unpooled.buffer();
        //byteBuf3.writeByte(0);
        //byteBuf3.writeByte(0x11);
        //dec 17  --hex 11
        demo.printDecHexConvert(byteBuf3, "0x11", 17);

        byteBuf3 = Unpooled.buffer();
        //byteBuf3.writeByte(0);
        //byteBuf3.writeByte(0x99);
        //dec 153  --hex 99
        demo.printDecHexConvert(byteBuf3, "ox99", 153);

        ByteBuf byteBuf4 = Unpooled.buffer();
        //byteBuf4.writeByte(0);
        //byteBuf4.writeByte(0xFF);
        //dec 255  --hex ff
        demo.printDecHexConvert(byteBuf4, "FF", 255);

    }

    private void printDecHexConvert(ByteBuf byteBuf, String hexStr, int decimalValue) {
        String strHex = Integer.toHexString(decimalValue);
        System.out.println("decimalValue:" + decimalValue + ", strHex:" + strHex);
        int length = strHex.getBytes(StandardCharsets.UTF_8).length;
//        if (length > 2) {
//            String str1 = strHex.substring(0, length -2);
//            BigInteger bigInteger = new BigInteger(str1,16);
//            Integer high = bigInteger.intValue();
//            byteBuf.writeByte(high.shortValue());
//
//            String str2 = strHex.substring( length -2,  length);
//            bigInteger = new BigInteger(str2,16);
//            Integer low = bigInteger.intValue();
//            byteBuf.writeByte(low.shortValue());
//            System.out.println("str1:" + str1 + ", high:" + high + ", str2:" + str2 + ", low:" + low);
//        } else {
//            byteBuf.writeByte(0);
//            String str2 = strHex.substring(0,  length);
//            BigInteger bigInteger = new BigInteger(str2,16);
//            Integer low = bigInteger.intValue();
//            byteBuf.writeByte(low.shortValue());
//            System.out.println("str1:0, high:0, str2:" + str2 + ", low:" + low);
//        }

        byteBuf.writeShort(decimalValue);

        long frameLength = (long)byteBuf.getUnsignedShort(0);

        long low = (long)byteBuf.getUnsignedByte(0);
        long high = (long)byteBuf.getUnsignedByte(1);
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(String.format("%02x", low)).append(String.format("%02x",high));
        String str = strBuilder.toString();
        BigInteger bigInteger = new BigInteger(str,16);
        long frameLength2 = Integer.valueOf(String.valueOf(frameLength), 16);
        System.out.println("decimalVal:" + decimalValue + ", HexStr:" + hexStr +
                ", getStr:" + str + "---" + frameLength + "," + bigInteger + "," +frameLength2);


    }
}
