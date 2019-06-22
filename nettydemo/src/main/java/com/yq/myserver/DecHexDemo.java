package com.yq.myserver;

/**
 * Created by yangqian on 2019/6/22.
 */
public class DecHexDemo {
    public static void main(String[] args) throws Exception {
        int a =255;
        byte byte1 = -128;
        short short1 = 0xff;
        int int11 = Byte.toUnsignedInt(byte1);
        System.out.println(int11);
        int int12 = Short.toUnsignedInt(short1);
        System.out.println(int12);

        byte byte2 = Byte.parseByte("0A", 16);
        System.out.println("0A -" +  byte2);
        byte byte3 = Byte.parseByte("7F", 16);
        System.out.println("FF -" +  byte3);

    }
}
