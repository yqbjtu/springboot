package com.yq.sample;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;

/**
 * Created by yangqian on 2019/6/16.
 */
public class MainDemo {
    public static void main(String[] args) throws Exception {
        String str = "21ni#hao88A中文Bn";
        byte[] strBytes = str.getBytes(StandardCharsets.UTF_8);
        String myHexStr = Hex.encodeHexString(strBytes);
        System.out.println("original str to hex");
        System.out.println(myHexStr);

        System.out.println("original str to decimal");
        for(int i=0; i< strBytes.length; i++) {
            byte singleByte = strBytes[i];
            System.out.print(singleByte);
        }

        //int decimalValue =255;
        //String bytes1 = Hex.encodeHexString( String.valueOf(decimalValue).getBytes());

        Integer decimalIntValue =255;
        String str00 = Integer.toString(decimalIntValue, 16); //ff
        byte[] byte00 = str00.getBytes();// 2 bytes 102, 102
        String str111 = Integer.toHexString(decimalIntValue);

        byte byte1 = decimalIntValue.byteValue();
        byte[] bytes1 = {byte1};
        String str1 = Hex.encodeHexString(bytes1);

        decimalIntValue =1255;
        Integer high = decimalIntValue /255 ;  //4
        Integer low = decimalIntValue % 255;  //235
        byte highByte = high.byteValue();
        byte lowByte = low.byteValue();

        String str22 = Integer.toString(decimalIntValue, 16); //4e7

        byte[] byte22 = str22.getBytes(); //3 bytes, 52,101, 55

        //"6A";
        Short shortValue = decimalIntValue.shortValue();

        byte[] bytes2 = {byte1};
        String str2 = Hex.encodeHexString(bytes2);

        byte[] strDecodeHexBytes = Hex.decodeHex(myHexStr.toCharArray());
        String strDecode = new String(strDecodeHexBytes, StandardCharsets.UTF_8);
        System.out.println("\r\nhex to original Str");

        System.out.println(strDecode);




//        String strHex = Integer.toHexString(in).toUpperCase();
//        System.out.println(strHex);
//        strHex = String.format("%5s",strHex);
//        String strHexZero = strHex.replaceAll(" ","0");
//        System.out.println(strHex);

    }


}
