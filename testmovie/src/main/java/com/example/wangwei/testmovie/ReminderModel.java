package com.example.wangwei.testmovie;

import java.util.ArrayList;

/**
 * Created by wangwei on 16/7/13.
 */
public class ReminderModel {
    public static String CREATE_REMINDER = "创建提醒成功";
    public static String REMIND_REMINDER = "小影提醒";
    public static String DELETE_REMINDER = "取消事件成功";
//    private String touser;
//    private String msgtype;
    private NewsModel news;

//    public String getTouser() {
//        return touser;
//    }
//
//    public void setTouser(String touser) {
//        this.touser = touser;
//    }
//
//    public String getMsgtype() {
//        return msgtype;
//    }
//
//    public void setMsgtype(String msgtype) {
//        this.msgtype = msgtype;
//    }

    public NewsModel getNews() {
        return news;
    }

    public void setNews(NewsModel news) {
        this.news = news;
    }

    public static class NewsModel {
        private ArrayList<ArticleModel> articles;

        public ArrayList<ArticleModel> getArticles() {
            return articles;
        }

        public void setArticles(ArrayList<ArticleModel> articles) {
            this.articles = articles;
        }

        public static class ArticleModel {
            private String title;
//            private String description;
//            private String picurl;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

//            public String getDescription() {
//                return description;
//            }
//
//            public void setDescription(String description) {
//                this.description = description;
//            }
//
//            public String getPicurl() {
//                return picurl;
//            }
//
//            public void setPicurl(String picurl) {
//                this.picurl = picurl;
//            }
        }
    }
}
