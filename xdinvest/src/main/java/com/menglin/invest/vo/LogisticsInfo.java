package com.menglin.invest.vo;

import java.util.List;

/**
 * Created by able on 2018/3/9.
 */

public class LogisticsInfo {

    /**
     * resultcode : 200
     * reason : 成功的返回
     * result : {"company":"圆通","com":"yt","no":"888452894000207010","status":"1","list":[{"datetime":"2018-02-28 13:19:38","remark":"【湖南省娄底市新化县公司】 已收件","zone":""},{"datetime":"2018-02-28 18:59:41","remark":"【湖南省娄底市新化县公司】 已打包","zone":""},{"datetime":"2018-02-28 19:07:01","remark":"【湖南省娄底市新化县公司】 已发出 下一站 【长沙转运中心】","zone":""},{"datetime":"2018-03-01 00:36:22","remark":"【长沙转运中心】 已收入","zone":""},{"datetime":"2018-03-01 00:41:57","remark":"【长沙转运中心】 已发出 下一站 【金华转运中心】","zone":""},{"datetime":"2018-03-01 17:26:20","remark":"【金华转运中心】 已收入","zone":""},{"datetime":"2018-03-01 17:29:22","remark":"【金华转运中心】 已发出 下一站 【杭州转运中心】","zone":""},{"datetime":"2018-03-01 21:12:13","remark":"【杭州转运中心】 已收入","zone":""},{"datetime":"2018-03-01 21:21:13","remark":"【杭州转运中心】 已发出 下一站 【浙江省杭州市康桥公司】","zone":""},{"datetime":"2018-03-02 08:15:20","remark":"【浙江省杭州市康桥公司】 已收入","zone":""},{"datetime":"2018-03-02 08:51:05","remark":"【浙江省杭州市康桥公司】 派件人: 陈浩 派件中 派件员电话18072712023","zone":""},{"datetime":"2018-03-02 12:35:47","remark":"客户 签收人: 本人签收 已签收 感谢使用圆通速递，期待再次为您服务","zone":""}]}
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private ResultBean result;
    private int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * company : 圆通
         * com : yt
         * no : 888452894000207010
         * status : 1
         * list : [{"datetime":"2018-02-28 13:19:38","remark":"【湖南省娄底市新化县公司】 已收件","zone":""},{"datetime":"2018-02-28 18:59:41","remark":"【湖南省娄底市新化县公司】 已打包","zone":""},{"datetime":"2018-02-28 19:07:01","remark":"【湖南省娄底市新化县公司】 已发出 下一站 【长沙转运中心】","zone":""},{"datetime":"2018-03-01 00:36:22","remark":"【长沙转运中心】 已收入","zone":""},{"datetime":"2018-03-01 00:41:57","remark":"【长沙转运中心】 已发出 下一站 【金华转运中心】","zone":""},{"datetime":"2018-03-01 17:26:20","remark":"【金华转运中心】 已收入","zone":""},{"datetime":"2018-03-01 17:29:22","remark":"【金华转运中心】 已发出 下一站 【杭州转运中心】","zone":""},{"datetime":"2018-03-01 21:12:13","remark":"【杭州转运中心】 已收入","zone":""},{"datetime":"2018-03-01 21:21:13","remark":"【杭州转运中心】 已发出 下一站 【浙江省杭州市康桥公司】","zone":""},{"datetime":"2018-03-02 08:15:20","remark":"【浙江省杭州市康桥公司】 已收入","zone":""},{"datetime":"2018-03-02 08:51:05","remark":"【浙江省杭州市康桥公司】 派件人: 陈浩 派件中 派件员电话18072712023","zone":""},{"datetime":"2018-03-02 12:35:47","remark":"客户 签收人: 本人签收 已签收 感谢使用圆通速递，期待再次为您服务","zone":""}]
         */

        private String company;
        private String com;
        private String no;
        private String status;
        private List<ListBean> list;

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getCom() {
            return com;
        }

        public void setCom(String com) {
            this.com = com;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * datetime : 2018-02-28 13:19:38
             * remark : 【湖南省娄底市新化县公司】 已收件
             * zone :
             */

            private String datetime;
            private String remark;
            private String zone;

            public String getDatetime() {
                return datetime;
            }

            public void setDatetime(String datetime) {
                this.datetime = datetime;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getZone() {
                return zone;
            }

            public void setZone(String zone) {
                this.zone = zone;
            }
        }
    }
}
