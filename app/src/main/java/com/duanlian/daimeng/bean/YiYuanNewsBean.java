package com.duanlian.daimeng.bean;

import java.util.List;

/**
 * 频道新闻API_易源的bean
 * http://apistore.baidu.com/apiworks/servicedetail/688.html
 */
public class YiYuanNewsBean {


    /**
     * showapi_res_code : 0
     * showapi_res_error :
     */

    private ShowapiResBodyBean showapi_res_body;
    private int showapi_res_code;
    private String showapi_res_error;

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public static class ShowapiResBodyBean {
        /**
         * ret_code : 0
         */

        private PagebeanBean pagebean;
        private int ret_code;

        public PagebeanBean getPagebean() {
            return pagebean;
        }

        public void setPagebean(PagebeanBean pagebean) {
            this.pagebean = pagebean;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public static class PagebeanBean {
            /**
             * allNum : 296601
             * allPages : 14831
             * currentPage : 1
             * maxResult : 20
             */

            private int allNum;
            private int allPages;
            private int currentPage;
            private int maxResult;
            private List<ContentlistBean> contentlist;

            public int getAllNum() {
                return allNum;
            }

            public void setAllNum(int allNum) {
                this.allNum = allNum;
            }

            public int getAllPages() {
                return allPages;
            }

            public void setAllPages(int allPages) {
                this.allPages = allPages;
            }

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public int getMaxResult() {
                return maxResult;
            }

            public void setMaxResult(int maxResult) {
                this.maxResult = maxResult;
            }

            public List<ContentlistBean> getContentlist() {
                return contentlist;
            }

            public void setContentlist(List<ContentlistBean> contentlist) {
                this.contentlist = contentlist;
            }

            public static class ContentlistBean {
                /**
                 * channelId : 5572a10ab3cdc86cf39001f4
                 * channelName : 科技最新
                 * desc : 距离百度原副总裁李明远辞职百度风波已经过去一个余月，然而事情似乎还未结束。时代周报记者在工商信息系统中留意到，李明远目前仍是百度旗下数家子公司的法定代表人，而今年5月已被开除的百度副总裁王湛亦存在类似的情况。根据往年季报的公布规律，百度的第四季度财报需要等到2017年2月才会发布，届时可能才披露更多详细
                 * havePic : true
                 * imageurls : [{"height":642,"url":"http://img1.gtimg.com/tech/pics/hv1/118/241/2166/140905723.jpg","width":451}]
                 * link : http://tech.qq.com/a/20161213/010065.htm
                 * pubDate : 2016-12-13 08:50:12
                 * source : 科技新闻
                 * title : 百度反腐风波后续：李明远仍是百度人？
                 * nid : 7072355016051191634
                 */

                private String channelId;
                private String channelName;
                private String desc;
                private boolean havePic;
                private String link;
                private String pubDate;
                private String source;
                private String title;
                private String nid;
                private List<ImageurlsBean> imageurls;

                public String getChannelId() {
                    return channelId;
                }

                public void setChannelId(String channelId) {
                    this.channelId = channelId;
                }

                public String getChannelName() {
                    return channelName;
                }

                public void setChannelName(String channelName) {
                    this.channelName = channelName;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public boolean isHavePic() {
                    return havePic;
                }

                public void setHavePic(boolean havePic) {
                    this.havePic = havePic;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getPubDate() {
                    return pubDate;
                }

                public void setPubDate(String pubDate) {
                    this.pubDate = pubDate;
                }

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getNid() {
                    return nid;
                }

                public void setNid(String nid) {
                    this.nid = nid;
                }

                public List<ImageurlsBean> getImageurls() {
                    return imageurls;
                }

                public void setImageurls(List<ImageurlsBean> imageurls) {
                    this.imageurls = imageurls;
                }


                public static class ImageurlsBean {
                    /**
                     * height : 642
                     * url : http://img1.gtimg.com/tech/pics/hv1/118/241/2166/140905723.jpg
                     * width : 451
                     */

                    private int height;
                    private String url;
                    private int width;

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }
                }
            }
        }
    }
}
