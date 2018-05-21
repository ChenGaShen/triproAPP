package com.menglin.triproapp.vo;

import java.util.List;

/** 
 * @author CGS 
 * @date 2018年3月16日 下午2:15:37 
 */
public class ShoppingListVO {
	
	private List<ResultListBean> resultList;

    public List<ResultListBean> getResultList() {
        return resultList;
    }

    public void setResultList(List<ResultListBean> resultList) {
        this.resultList = resultList;
    }

    public static class ResultListBean {
        /**
         * id : 37
         * commodityId : 1
         * num : 1
         * uid : 3
         * state : null
         * ip : null
         * ipAddress : null
         * addTime : 1521099352000
         * updateTime : 1521099352000
         * name : 多趣宝 TRIPRO 狗粮
         * specification : 一包
         * img : http://duoqubao.hzmenglin.com/img/QQ图片20180306142929.jpg
         * commodityPrice : 0.01
         */

        private int id;
        private int commodityId;
        private int num;
        private int uid;
        private Object state;
        private Object ip;
        private Object ipAddress;
        private long addTime;
        private long updateTime;
        private String name;
        private String specification;
        private String img;
        private String commodityPrice;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public Object getIp() {
            return ip;
        }

        public void setIp(Object ip) {
            this.ip = ip;
        }

        public Object getIpAddress() {
            return ipAddress;
        }

        public void setIpAddress(Object ipAddress) {
            this.ipAddress = ipAddress;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCommodityPrice() {
            return commodityPrice;
        }

        public void setCommodityPrice(String commodityPrice) {
            this.commodityPrice = commodityPrice;
        }
    }

}
