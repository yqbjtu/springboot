package com.yq;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;

/**
 * 一个十六进制数（Hex），正好为4个二进制位。一个字节（byte）为8个二进制位。因此，一个字节可表示为两个十六进制数字
 * Created by yangqian on 2019/6/16.
 */
public class MainDemo {
    public static void main(String[] args) throws Exception {
        String str = "21ni#hao88A中文Bn";
        byte[] strBytes = str.getBytes(StandardCharsets.UTF_8);
        String myHexStr = Hex.encodeHexString(strBytes);
        char[] chars  = Hex.encodeHex(strBytes);
        System.out.println("chars:");
        for(int i=0; i< chars.length; i++) {
            char singleChar = chars[i];
            System.out.print(singleChar);
        }
        System.out.println("");

        byte[] strHexBytes = myHexStr.getBytes(StandardCharsets.UTF_8);
        System.out.println("original str to hex. size:" + strHexBytes.length);
        System.out.println("hex str");
        System.out.println(myHexStr);
        System.out.println("hex byte");
        for(int i=0; i< strHexBytes.length; i++) {
            byte singleByte = strHexBytes[i];
            System.out.print(singleByte);
        }

        System.out.println("\r\noriginal str to decimal. size:" +  strBytes.length);
        for(int i=0; i< strBytes.length; i++) {
            byte singleByte = strBytes[i];
            System.out.print(singleByte);
        }


        byte[] strDecodeHexBytes = Hex.decodeHex(myHexStr.toCharArray());
        String strDecode = new String(strDecodeHexBytes, StandardCharsets.UTF_8);
        System.out.println("\r\nhex to original Str");

        System.out.println(strDecode);
        Hex myHex = new Hex();
        byte[] decodedHexBytes = myHex.encode(strDecodeHexBytes);
        System.out.println("\r\n hex bytes to decimal bytes. size:" +  decodedHexBytes.length);
        for(int i=0; i< decodedHexBytes.length; i++) {
            byte singleByte = decodedHexBytes[i];
            System.out.print(singleByte);
        }




//        String strHex = Integer.toHexString(in).toUpperCase();
//        System.out.println(strHex);
//        strHex = String.format("%5s",strHex);
//        String strHexZero = strHex.replaceAll(" ","0");
//        System.out.println(strHex);

        //实际当中可能受到就是一组byte, 他们是16进制,先转为为10进制, 然后字符串
        System.out.println("");
        byte[] hexBytes = {0xF, 0xA};
        for(int i=0; i< hexBytes.length; i++) {
            byte singleByte = hexBytes[i];
            System.out.print(singleByte);
        }


    }


}
