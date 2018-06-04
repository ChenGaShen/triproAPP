package com.menglin.triproapp.vo;
/** 
 * @author CGS 
 * @date 2018年5月22日 下午3:48:07 
 */
public class CommodityListInfo {
	
	 	private Integer commodityId;

	    private String commodityName;

	    private String price; 

	    private String discountPrice;
	    
	    private String commodityImg;

		public Integer getCommodityId() {
			return commodityId;
		}

		public void setCommodityId(Integer commodityId) {
			this.commodityId = commodityId;
		}

		public String getCommodityName() {
			return commodityName;
		}

		public void setCommodityName(String commodityName) {
			this.commodityName = commodityName;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getDiscountPrice() {
			return discountPrice;
		}

		public void setDiscountPrice(String discountPrice) {
			this.discountPrice = discountPrice;
		}

		public String getCommodityImg() {
			return commodityImg;
		}

		public void setCommodityImg(String commodityImg) {
			this.commodityImg = commodityImg;
		}
	    
	    
	    
}
