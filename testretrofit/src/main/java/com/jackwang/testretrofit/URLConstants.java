package com.jackwang.testretrofit;

/**
 * Created by wangwei on 17/4/11.
 */

public class URLConstants {
//    public static final String HOST= "http://idc.emotibot.com";
//    public static final String API_PATH= "/api/ApiKey/openapi.php";
    public static final String HOST= "http://172.16.106.70";
    public static final String API_PATH= "/phpfile/json/json.php";
    public static final String BASE_API= HOST+API_PATH;


    public static final String PARAM_NAME_CMD = "cmd";
    public static final String PARAM_NAME_APPID= "appid";
    public static final String PARAM_NAME_USERID= "userid";
    public static final String PARAM_NAME_TEXT= "text";
    public static final String PARAM_NAME_JSON= "json";


    public static final String PARAM_VALUE_CMD_REGISTRATION = "register";
    public static final String PARAM_VALUE_CMD_CHAT = "chat";


}
