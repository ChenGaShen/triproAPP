package com.menglin.triproapp.vo;

import java.util.List;

/**
 * Created by able on 2017/8/24.
 */

public class OrderJsonVO {


    /**
     * result : null
     * orderId : 20180308105032537300
     * orderItems : [{"orderId":"20180308105032537300","img":"http://duoqubao.hzmenglin.com/img/QQ图片20180306142929.jpg","commodityName":"多趣宝 TRIPRO 狗粮","amount":1,"price":"180.00","commodityId":1,"specification":"一包"},{"orderId":"20180308105032537300","img":"http://duoqubao.hzmenglin.com/img/QQ图片20180306142929.jpg","commodityName":"大壮猫粮","amount":1,"price":"200.00","commodityId":2,"specification":"2包"}]
     * state : 已付款
     * receiveState : 待发货
     * receiveName : 闫旭东
     * receivePhone : 15135224872
     * receiveAddress : 123123
     * addTime : 2018-03-08 10:50:32
     * orderPrice : 380.00
     */

    private Object result;
    private String orderId;
    private String state;
    private String receiveState;
    private String receiveName;
    private String receivePhone;
    private String receiveAddress;
    private String addTime;
    private String orderPrice;
    private List<OrderItemsBean> orderItems;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReceiveState() {
        return receiveState;
    }

    public void setReceiveState(String receiveState) {
        this.receiveState = receiveState;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public List<OrderItemsBean> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemsBean> orderItems) {
        this.orderItems = orderItems;
    }

    public static class OrderItemsBean {
        /**
         * orderId : 20180308105032537300
         * img : http://duoqubao.hzmenglin.com/img/QQ图片20180306142929.jpg
         * commodityName : 多趣宝 TRIPRO 狗粮
         * amount : 1
         * price : 180.00
         * commodityId : 1
         * specification : 一包
         */

        private String orderId;
        private String img;
        private String commodityName;
        private int amount;
        private String price;
        private int commodityId;
        private String specification;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }
    }
}
