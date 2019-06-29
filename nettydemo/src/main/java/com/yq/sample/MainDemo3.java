package com.yq.sample;

import java.util.ArrayList;
import java.util.List;

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


        List<Integer> byteList = new ArrayList<>();
        byteList.add(2);
        byteList.add(-77);

        byte[] bytes = {byteList.get(0).byteValue(), byteList.get(1).byteValue()};
        short shortValue = byte2ToShort(bytes);
        System.out.println("shortValues:" + shortValue);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            String result = Integer.toBinaryString(byteList.get(i).byteValue());
            int length = result.length();
            String result1 = "";
            if (length < 8) {
                for (int j=0; j < (8 - length); j++) {
                    result1 = result1 + "0";
                }
                result1 += result;
            } else {
                result1 = result.substring(0, 8);
            }

            System.out.println("result1:" + result1);

            stringBuilder.append(result1);
        }
        int a = 0b11;

        String result = stringBuilder.toString();
        Integer resultInt = Integer.valueOf("0000001011111111", 2);
        System.out.println("resultInt:" + resultInt + ",result:" + result);


    }
    public static byte[] intToByte4(int val) {
        byte[] arr = new byte[4];
        arr[0] = (byte) (val >> 24);
        arr[1] = (byte) (val >> 16);
        arr[2] = (byte) (val >> 8);
        arr[3] = (byte) (val & 0xff);
        return arr;
    }

    public static int byte4ToInt(byte[] arr) {
        if (arr == null || arr.length != 4) {
            throw new IllegalArgumentException("byte数组不能为空,并且是4位!");
        }
        return (int) (((arr[0] & 0xff) << 24) | ((arr[1] & 0xff) << 16) | ((arr[2] & 0xff) << 8) | ((arr[3] & 0xff)));
    }

    public static short byte2ToShort(byte[] arr) {
        if (arr != null && arr.length != 2) {
            throw new IllegalArgumentException("byte数组不能为空,并且是2位!");
        }
        return (short) (((short) arr[0] << 8) | ((short) arr[1] & 0xff));
    }

    public static long byte8ToLong(byte[] arr) {
        if (arr == null || arr.length != 8) {
            throw new IllegalArgumentException("byte数组不能为空,并且是8位!");
        }
        return (long) (((long) (arr[0] & 0xff) << 56) | ((long) (arr[1] & 0xff) << 48)
                | ((long) (arr[2] & 0xff) << 40) | ((long) (arr[3] & 0xff) << 32) | ((long) (arr[4] & 0xff) << 24)
                | ((long) (arr[5] & 0xff) << 16) | ((long) (arr[6] & 0xff) << 8) | ((long) (arr[7] & 0xff)));
    }

    public static byte[] longToByte8(long sum) {
        byte[] arr = new byte[8];
        arr[0] = (byte) (sum >> 56);
        arr[1] = (byte) (sum >> 48);
        arr[2] = (byte) (sum >> 40);
        arr[3] = (byte) (sum >> 32);
        arr[4] = (byte) (sum >> 24);
        arr[5] = (byte) (sum >> 16);
        arr[6] = (byte) (sum >> 8);
        arr[7] = (byte) (sum & 0xff);
        return arr;
    }
}
