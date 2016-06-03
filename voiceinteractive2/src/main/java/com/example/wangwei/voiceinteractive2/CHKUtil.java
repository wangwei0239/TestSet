package com.example.wangwei.voiceinteractive2;

import org.xutils.common.util.MD5;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Hector on 16/3/28.
 */
public class CHKUtil {
    public static String getCHKNum(String para) {
        String result = null;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyyHHss");//年时秒
        String prefix = sdf.format(date);
        sdf.applyPattern("MMddmm");//月日分钟
        String surfix = sdf.format(date);
        String rawResult = prefix + para + surfix;
        String rawResultMd5 = MD5.md5(rawResult);
        char[] array = {rawResultMd5.charAt(2), rawResultMd5.charAt(9), rawResultMd5.charAt(15), rawResultMd5.charAt(17)};
        result = String.valueOf(array);
        sdf.applyPattern("yyyyMMddHHmmss");
//        String timeString = sdf.format(date);
//        System.out.println("md5:"+result+" time:"+timeString);
        return result;
    }

    public static String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyyMMddHHmmss");
        Date date = new Date();
        return sdf.format(date);
    }

    public static String getCHKNum(Date date, String para) {
        String result = null;
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyyHHss");//年时秒
        String prefix = sdf.format(date);
        sdf.applyPattern("MMddmm");//月日分钟
        String surfix = sdf.format(date);
        String rawResult = prefix + para + surfix;
        String rawResultMd5 = MD5.md5(rawResult);
        char[] array = {rawResultMd5.charAt(2), rawResultMd5.charAt(9), rawResultMd5.charAt(15), rawResultMd5.charAt(17)};
        result = String.valueOf(array);
        return result;
    }

    public static String getTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    public static String generateParameterForGetMessage(PreferencesUtils preferencesUtils) {
        //UserID=xxx&cmd=ASK&chk=num&time=yyyymmddHHMMSS
        Date date = new Date();
        int uid = preferencesUtils.getInt("userId");
        String chk = CHKUtil.getCHKNum(date, ""+uid);
        String timeString = CHKUtil.getTime(date);

        String ret = "UserID="+uid+"&cmd=ASK&chk="+chk+"&time="+timeString;

        return ret;
    }

    public static String generateParameterForGet(PreferencesUtils preferencesUtils) {
        //UserID=xxx&cmd=ASK&chk=num&time=yyyymmddHHMMSS
        Date date = new Date();
        int uid = preferencesUtils.getInt("userId");
        String chk = CHKUtil.getCHKNum(date, ""+uid);
        String timeString = CHKUtil.getTime(date);

        String ret = "UserID="+uid+"&chk="+chk+"&time="+timeString;

        return ret;
    }
}
