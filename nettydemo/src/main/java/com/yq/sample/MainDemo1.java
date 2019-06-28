package com.yq.sample;

import com.sun.deploy.util.StringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.ArrayList;

/**
 * Simple to Introduction
 * className: MainDemo1
 *
 * @author EricYang
 * @version 2019/6/21 16:04
 */
public class MainDemo1 {

    public static void main(String[] args) throws Exception {
        String str = "006a";
        int length = Integer.parseInt(str,16);
        System.out.println("original str to decimal");
        System.out.println("str to decimal." + length);

        Integer v1 = 254;
        Short v1short = v1.shortValue();
        ByteBuf buf = Unpooled.buffer();
        buf.writeByte(v1.byteValue());
        buf.writeByte(v1short.byteValue());

        byte a = buf.getByte(0);
        String str2 = String.format("%02x", a);

        System.out.println("str to decimal." + str2);
        System.out.println("str to decimal." + str2);

        int valueTen = 12;
        String strHex = Integer.toHexString(valueTen);
        int newValue = Integer.valueOf(strHex, 16);

        String strHex2 = String.valueOf(valueTen);
        int newValue2 = Integer.valueOf(strHex2, 16);

        System.out.println("str to decimal." + str2);
        ArrayList<String> datas = new ArrayList<>();
        datas.add(String.format("%02x", 0));
        //datas.add(String.format("%02x", 12));
        datas.add("6a");
        String str3 = "000c";
        String str4 = StringUtils.join(datas, "");

        int frameLength = Integer.valueOf(str4, 16);
        //int frameLength = Integer.valueOf(datas.get(0), 16);
        System.out.println("str to frameLength." + frameLength);

    }

}
