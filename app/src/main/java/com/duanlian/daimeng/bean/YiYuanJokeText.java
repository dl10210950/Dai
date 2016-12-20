package com.duanlian.daimeng.bean;

import java.util.List;

/**
 * 笑话的bean
 */

public class YiYuanJokeText {
    public int showapi_res_code;
    public String showapi_res_error;
    public ShowapiResBodyBean showapi_res_body;

    public static class ShowapiResBodyBean {
        public int allPages;
        public int currentPage;
        public List<ContentlistBean> contentlist;
    }

    public static class ContentlistBean {
        public String ct;
        public String id;
        public String text;
        public String title;
        public int type;
    }

}
