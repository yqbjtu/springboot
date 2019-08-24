package com.yq.sample;

/**
 * Created by yangqian on 2019/6/22.
 */
public class DecDemo {
    public static void main(String[] args) throws Exception {
        String str = "10101011";
        //String str = "0e";
        Integer bigInteger1 =  Integer.parseInt(str,2);
        String str1 = "-43";
        Byte myByte =  Byte.parseByte(str1);

        System.out.println( bigInteger1 + ", " + myByte);

    }


}
