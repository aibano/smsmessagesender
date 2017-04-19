package com.sejelli.messaging.services.mobilywssp;

import java.io.UnsupportedEncodingException;

/**
 * Created by aibano on 4/19/2017.
 */
public class MobilyCharsetConverter {

    public static String covert(String message) throws UnsupportedEncodingException {
        String message1 = new String(message.getBytes("cp1256"), "cp1256");
        return convertUnicode(message1);
    }

    private static String convertUnicode(String str) {
        char[] chars = str.toCharArray();
        StringBuffer strBuffer = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            strBuffer.append(forDigits( Integer.toHexString((int) chars[i])));
        }
        return strBuffer.toString();
    }

    private static String forDigits(String val){
        switch (val.length() ){
            case 1:return "000"+val;
            case 2:return "00"+val;
            case 3:return "0"+val;
            case 4:return ""+val;
            default:return val;
        }
    }
}
