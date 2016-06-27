package com.example.wangwei.voiceinteractive2.Model;

import java.util.ArrayList;

/**
 * Created by wangwei on 16/6/2.
 */
public class SpeechRecognitionResultModel {
    private int sn;
    private boolean ls;
    private int bg;
    private int ed;
    private ArrayList<WSModel> ws;

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public boolean isLs() {
        return ls;
    }

    public void setLs(boolean ls) {
        this.ls = ls;
    }

    public int getBg() {
        return bg;
    }

    public void setBg(int bg) {
        this.bg = bg;
    }

    public int getEd() {
        return ed;
    }

    public void setEd(int ed) {
        this.ed = ed;
    }

    public ArrayList<WSModel> getWs() {
        return ws;
    }

    public void setWs(ArrayList<WSModel> ws) {
        this.ws = ws;
    }

    public static class WSModel{
        private int bg;
        private ArrayList<CWModel> cw;

        public int getBg() {
            return bg;
        }

        public void setBg(int bg) {
            this.bg = bg;
        }

        public ArrayList<CWModel> getCw() {
            return cw;
        }

        public void setCw(ArrayList<CWModel> cw) {
            this.cw = cw;
        }

        public static class CWModel{
            private double sc;
            private String w;

            public double getSc() {
                return sc;
            }

            public void setSc(double sc) {
                this.sc = sc;
            }

            public String getW() {
                return w;
            }

            public void setW(String w) {
                this.w = w;
            }
        }
    }
}
