package com.yq;

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

        int decimalValue =255;
        byte[] bytes1 = Hex.decodeHex( String.valueOf(decimalValue).toCharArray());

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
