package com.soundai.smartboxlite.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangwei on 17/4/11.
 */

public class User implements Serializable{

    /**
     * return : 0
     * return_message :
     * status : 0
     * data : [{"type":"text","cmd":"register","value":"0E52E1B52BF804B4AED4A82D9B498A3B8","data":[]}]
     * emotion : []
     */

    @com.google.gson.annotations.SerializedName("return")
    private int returnX;
    private String return_message;
    private int status;
    private List<DataBean> data;
    private List<?> emotion;

    public int getReturnX() {
        return returnX;
    }

    public void setReturnX(int returnX) {
        this.returnX = returnX;
    }

    public String getReturn_message() {
        return return_message;
    }

    public void setReturn_message(String return_message) {
        this.return_message = return_message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<?> getEmotion() {
        return emotion;
    }

    public void setEmotion(List<?> emotion) {
        this.emotion = emotion;
    }

    public static class DataBean {
        /**
         * type : text
         * cmd : register
         * value : 0E52E1B52BF804B4AED4A82D9B498A3B8
         * data : []
         */

        private String type;
        private String cmd;
        private String value;
        private List<?> data;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCmd() {
            return cmd;
        }

        public void setCmd(String cmd) {
            this.cmd = cmd;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<?> getData() {
            return data;
        }

        public void setData(List<?> data) {
            this.data = data;
        }
    }
}
