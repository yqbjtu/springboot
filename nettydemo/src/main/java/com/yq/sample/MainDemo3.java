package com.yq.sample;

/**
 * Created by yangqian on 2019/6/16.
 */
public class MainDemo3 {
    public static void main(String[] args) throws Exception {
        Integer decimalIntValue = 691;
        byte byte1 = decimalIntValue.byteValue();
        short short1 = decimalIntValue.shortValue();

        System.out.println("\r\nhex to original Str:" + byte1 + "," + short1);


        int val = decimalIntValue;
        byte[] b = new byte[4];
        b[0] = (byte) (val & 0xff);
        b[1] = (byte) ((val >> 8) & 0xff);
        b[2] = (byte) ((val >> 16) & 0xff);
        b[3] = (byte) ((val >> 24) & 0xff);
        System.out.println("int to bytes:" + b[0] + "," + b[1] + "," + b[2] + "," + b[3]);


        //decimalIntValue.t

    }


}
