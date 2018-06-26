package com.menglin.triproapp.controller;

import java.io.File;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.menglin.triproapp.entity.Admin;
import com.menglin.triproapp.entity.Commodity;
import com.menglin.triproapp.entity.CommodityDetails;
import com.menglin.triproapp.entity.CommoditySeckill;
import com.menglin.triproapp.service.ICommodityDetailImgService;
import com.menglin.triproapp.service.ICommoditySeckillService;
import com.menglin.triproapp.service.ICommodityService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.Format;
import com.menglin.triproapp.util.ImgPressThread;
import com.menglin.triproapp.util.PageBean;
import com.menglin.triproapp.util.Result;
import com.menglin.triproapp.util.SystemParam;
import com.menglin.triproapp.util.file;
import com.menglin.triproapp.vo.CommodityDetailImgVO;
import com.menglin.triproapp.vo.CommoditySeckillDetailVO;
import com.menglin.triproapp.vo.PageRuslt;
import com.menglin.triproapp.vo.ResultObject;
import com.menglin.triproapp.vo.ResultVN;


/** 
 * @author CGS 
 * @date 2018年2月1日 下午1:33:48 
 */
@Controller  
@RequestMapping("/admin/commoditySeckill")
public class CommoditySeckillController {
	
	@Resource  
    private ICommoditySeckillService commoditySeckillService;
	
	@Resource  
    private ICommodityDetailImgService  commodityDetailImgService;
	
	
	
	
	/**
	 * 秒杀商品列表分页
	 * @author CGS
	 * @time 2018年3月22日下午1:43:31
	 * @param currentPage
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findByPage.json",method={RequestMethod.POST})  
    public @ResponseBody PageRuslt<CommoditySeckillDetailVO> findByPage(int currentPage, int pageSize,CommoditySeckill model){
		PageRuslt<CommoditySeckillDetailVO> pageRuslt =new PageRuslt<CommoditySeckillDetailVO>();
		PageBean<CommoditySeckillDetailVO> PageUser=commoditySeckillService.findByPage(currentPage, pageSize, model);
		pageRuslt.setPageBean(PageUser);
      return pageRuslt;
       
	}
	
	/**
	 * 商品详情
	 * @author CGS
	 * @time 2018年3月22日下午1:51:05
	 * @param commodityId
	 * @return
	 */
	@RequestMapping(value="/commoditySeckillDetail.json",method={RequestMethod.POST})  
	public @ResponseBody CommoditySeckillDetailVO commodityDetail(Integer commodityId){
		CommoditySeckill commoditySeckill =commoditySeckillService.get(commodityId);
		CommoditySeckillDetailVO vo=new CommoditySeckillDetailVO();
		if (CheckData.allfieldIsNotNUll(commoditySeckill)) {
			vo.setCommodityseckillId(commoditySeckill.getCommodityseckillId());
			vo.setCommodityseckillName(commoditySeckill.getCommodityseckillName());
			vo.setSeckillPrice(Format.keepTwoMoney(commoditySeckill.getSeckillPrice()));
			vo.setSeckillDiscountprice(Format.keepTwoMoney(commoditySeckill.getSeckillDiscountprice()));
			vo.setSeckillAmount(commoditySeckill.getSeckillAmount());
			vo.setSeckillAllowance(commoditySeckill.getSeckillAllowance());
			vo.setSeckillCommodityimg(SystemParam.DOMAIN_NAME+commoditySeckill.getSeckillCommodityimg());
			vo.setSeckillSpecification(commoditySeckill.getSeckillSpecification());
			vo.setSeckillRealSale(commoditySeckill.getSeckillRealSale());
			vo.setSeckillVirtualSales(commoditySeckill.getSeckillVirtualSales());
			vo.setSeckillOnsale(commoditySeckill.getSeckillOnsale());//是否出售
			vo.setSeckillState(commoditySeckill.getSeckillState());//是否开启秒杀
			vo.setSeckillClassify(commoditySeckill.getSeckillClassify());
			vo.setAddTime(commoditySeckill.getAddTime());
			vo.setStartTime(commoditySeckill.getStartTime());
			vo.setEndTime(commoditySeckill.getEndTime());
			vo.setSeckillDescription(commoditySeckill.getSeckillDescription());
		}
		return vo;
	}
	
	/**
	 * 商品新增
	 * @author CGS
	 * @time 2018年2月2日下午2:19:57
	 * @param model
	 * @param request
	 * @param f
	 * @return
	 * @throws IOException
	 * @throws ParseException 
	 */
	@RequestMapping(value="/addCommoditySeckill.json",method={RequestMethod.POST})
	public @ResponseBody ResultVN addCommodity(CommoditySeckill model, String seckillStartTime,String seckillEndTime, MultipartHttpServletRequest request,file f) throws IOException, ParseException {
		ResultVN vn =new ResultVN();
		
			/*if (model.getName() == null || "".equals(model.getName())) {
				m.addObject(Result.fal("请填写商品名!"));
			}else if (model.getPrice() == null || model.getPrice() == 0) {
				m.addObject(Result.fal("请填写商品单价!"));
			}else if (model.getAmount() == null || model.getAmount() == 0) {
				m.addObject(Result.fal("请填写商品数量!"));
			} else {*/
				// 本地
				List<String> lis=new ArrayList<String>();
				Iterator<String> iter = request.getFileNames();
				while (iter.hasNext()) {
					MultipartFile file = request.getFile(iter.next());
					if (file != null) {
						if (file.getName().equals("file")) {
							String fileName = file.getOriginalFilename();
							if (fileName.trim() != "") {
								Date date = new Date();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
								String timestamp = sdf.format(date);
//								String basePath = request.getSession().getServletContext().getRealPath("/");
								String basePath = "E:\\images\\uploadApp\\";
								fileName=fileName.substring(fileName.lastIndexOf("."));
								String path =timestamp+"_mlmiaosha"+fileName;// 文件保存路径
								File localFile = new File(basePath + path);
								file.transferTo(localFile);
//								Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
								new ImgPressThread(localFile).start();//多线程压缩图片
								lis.add(path);
								model.setSeckillCommodityimg("/imgApp/"+path);
							   
							}
						}
						
				}
					
	 }			model.setCommodityseckillName(model.getCommodityseckillName());//商品名称
				model.setSeckillPrice(Format.formatMoney(model.getSeckillPrice()));//商品普通价格
				model.setSeckillDiscountprice(Format.formatMoney(model.getSeckillDiscountprice()));//商品供销商价格
				model.setSeckillAmount(model.getSeckillAmount());//库存
				model.setSeckillAllowance(model.getSeckillAllowance());//总量
				model.setSeckillDescription(model.getSeckillDescription());//描述
				model.setSeckillSpecification(model.getSeckillSpecification());// 规格
				model.setSeckillOnsale(0);//商品状态0上架1下架
				model.setSeckillState(1);//商品秒杀状态0开启1关闭 -----定时开启
				model.setSeckillRealSale(0);//实际销量
				model.setSeckillVirtualSales(model.getSeckillVirtualSales());//虚拟销量
				model.setSeckillClassify(model.getSeckillClassify());
				model.setAddTime(new Date());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println("startTime1:"+seckillStartTime);
				System.out.println("startTime2:"+sdf.parse(seckillStartTime));
				model.setStartTime(sdf.parse(seckillStartTime));
				System.out.println("endTime1:"+seckillEndTime);
				System.out.println("endTime2:"+sdf.parse(seckillEndTime));
				model.setEndTime(sdf.parse(seckillEndTime));
				commoditySeckillService.save(model);
				vn.setResult(Result.suc("商品秒杀添加成功!!"));
				
				return vn;
  }
	
	
	/**
	 * 商品详情图新增/更新
	 * @author CGS
	 * @time 2018年5月22日下午2:55:02
	 * @param model
	 * @param request
	 * @param f
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/addCommoditySeckillDetailImg.json",method={RequestMethod.POST})
	public @ResponseBody ResultVN addCommodityDetailImg(CommodityDetails model, MultipartHttpServletRequest request,file f) throws IOException {
			ResultVN vn =new ResultVN();
			CommoditySeckill commoditySeckill =commoditySeckillService.get(model.getSeckillId());
			if (CheckData.isNotNullOrEmpty(commoditySeckill)) {
				List<String> lis=new ArrayList<String>();
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String timestamp = sdf.format(date);
				//区分新增还是更新？
				CommodityDetails commodityDetails =commodityDetailImgService.get(model.getDetailsid());
				if (CheckData.isNotNullOrEmpty(commodityDetails)) {
					//更新路径
					Iterator<String> iter = request.getFileNames();
					while (iter.hasNext()) {
						MultipartFile file = request.getFile(iter.next());
						if (file != null) {
							if (file.getName().equals("file01")) {
								String fileName = file.getOriginalFilename();
								if (fileName.trim() != "") {
									long  startTime=System.currentTimeMillis();
//									String basePath = request.getSession().getServletContext().getRealPath("/");
									String basePath = "E:\\images\\uploadApp\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path =timestamp+"_ml01"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
//									Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									lis.add(path);
									commodityDetails.setDetails01("/imgApp/"+path);
									System.out.println(SystemParam.DOMAIN_NAME+commodityDetails.getDetails01());
								
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							if (file.getName().equals("file02")) {
								String fileName = file.getOriginalFilename();
								if (fileName.trim() != "") {
									long  startTime=System.currentTimeMillis();
									
//									String basePath = request.getSession().getServletContext().getRealPath("/");
									String basePath = "E:\\images\\uploadApp\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path =timestamp+"_ml02"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
//									Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									lis.add(path);
									commodityDetails.setDetails02("/imgApp/"+path);
									System.out.println(SystemParam.DOMAIN_NAME+commodityDetails.getDetails02());
								
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							if (file.getName().equals("file03")) {
								String fileName = file.getOriginalFilename();
								if (fileName.trim() != "") {
									long  startTime=System.currentTimeMillis();
									
//									String basePath = request.getSession().getServletContext().getRealPath("/");
									String basePath = "E:\\images\\uploadApp\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path =timestamp+"_ml03"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
//									Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									lis.add(path);
									commodityDetails.setDetails03("/imgApp/"+path);
									System.out.println(SystemParam.DOMAIN_NAME+commodityDetails.getDetails03());
								
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							if (file.getName().equals("file04")) {
								String fileName = file.getOriginalFilename();
								if (fileName.trim() != "") {
									long  startTime=System.currentTimeMillis();
									
//									String basePath = request.getSession().getServletContext().getRealPath("/");
									String basePath = "E:\\images\\uploadApp\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path =timestamp+"_ml04"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
//									Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									lis.add(path);
									commodityDetails.setDetails04("/imgApp/"+path);
									System.out.println(SystemParam.DOMAIN_NAME+commodityDetails.getDetails04());
								
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							if (file.getName().equals("file05")) {
								String fileName = file.getOriginalFilename();
								if (fileName.trim() != "") {
									long  startTime=System.currentTimeMillis();
									
//									String basePath = request.getSession().getServletContext().getRealPath("/");
									String basePath = "E:\\images\\uploadApp\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path =timestamp+"_ml05"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
//									Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									lis.add(path);
									commodityDetails.setDetails05("/imgApp/"+path);
									System.out.println(SystemParam.DOMAIN_NAME+commodityDetails.getDetails05());
								
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							if (file.getName().equals("file06")) {
								String fileName = file.getOriginalFilename();
								if (fileName.trim() != "") {
									long  startTime=System.currentTimeMillis();
									
//									String basePath = request.getSession().getServletContext().getRealPath("/");
									String basePath = "E:\\images\\uploadApp\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path =timestamp+"_ml06"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
//									Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									lis.add(path);
									commodityDetails.setDetails06("/imgApp/"+path);
									System.out.println(SystemParam.DOMAIN_NAME+commodityDetails.getDetails06());
								
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							if (file.getName().equals("file07")) {
								String fileName = file.getOriginalFilename();
								if (fileName.trim() != "") {
									long  startTime=System.currentTimeMillis();
									
//									String basePath = request.getSession().getServletContext().getRealPath("/");
									String basePath = "E:\\images\\uploadApp\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path =timestamp+"_ml07"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
//									Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									lis.add(path);
									commodityDetails.setDetails07("/imgApp/"+path);
									System.out.println(SystemParam.DOMAIN_NAME+commodityDetails.getDetails07());
								
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							if (file.getName().equals("file08")) {
								String fileName = file.getOriginalFilename();
								if (fileName.trim() != "") {
									long  startTime=System.currentTimeMillis();
									
//									String basePath = request.getSession().getServletContext().getRealPath("/");
									String basePath = "E:\\images\\uploadApp\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path =timestamp+"_ml08"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
//									Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									lis.add(path);
									commodityDetails.setDetails08("/imgApp/"+path);
									System.out.println(SystemParam.DOMAIN_NAME+commodityDetails.getDetails08());
								
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							if (file.getName().equals("file09")) {
								String fileName = file.getOriginalFilename();
								if (fileName.trim() != "") {
									long  startTime=System.currentTimeMillis();
									
//									String basePath = request.getSession().getServletContext().getRealPath("/");
									String basePath = "E:\\images\\uploadApp\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path =timestamp+"_ml09"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
//									Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									lis.add(path);
									commodityDetails.setDetails09("/imgApp/"+path);
									System.out.println(SystemParam.DOMAIN_NAME+commodityDetails.getDetails09());
								
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							if (file.getName().equals("file10")) {
								String fileName = file.getOriginalFilename();
								if (fileName.trim() != "") {
									long  startTime=System.currentTimeMillis();
									
//									String basePath = request.getSession().getServletContext().getRealPath("/");
									String basePath = "E:\\images\\uploadApp\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path =timestamp+"_ml10"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
//									Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									lis.add(path);
									commodityDetails.setDetails10("/imgApp/"+path);
									System.out.println(SystemParam.DOMAIN_NAME+commodityDetails.getDetails10());
								
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							
					    }
				    }
					commodityDetailImgService.update(commodityDetails);
					vn.setResult(Result.suc("商品详情图已更新!!"));
					// 详情图更新 完毕
				}else{
					//新增路径
					Iterator<String> iter = request.getFileNames();
					while (iter.hasNext()) {
						MultipartFile file = request.getFile(iter.next());
						if (file != null) {
							if (file.getName().equals("file01")) {
								String fileName = file.getOriginalFilename();
								if (fileName.trim() != "") {
									long  startTime=System.currentTimeMillis();
//									String basePath = request.getSession().getServletContext().getRealPath("/");
									String basePath = "E:\\images\\uploadApp\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path =timestamp+"_ml01"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
//									Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									lis.add(path);
									model.setDetails01("/imgApp/"+path);
									System.out.println(SystemParam.DOMAIN_NAME+model.getDetails01());
								
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							if (file.getName().equals("file02")) {
								String fileName = file.getOriginalFilename();
								if (fileName.trim() != "") {
									long  startTime=System.currentTimeMillis();
									
//									String basePath = request.getSession().getServletContext().getRealPath("/");
									String basePath = "E:\\images\\uploadApp\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path =timestamp+"_ml02"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
//									Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									lis.add(path);
									model.setDetails02("/imgApp/"+path);
									System.out.println(SystemParam.DOMAIN_NAME+model.getDetails02());
								
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							if (file.getName().equals("file03")) {
								String fileName = file.getOriginalFilename();
								if (fileName.trim() != "") {
									long  startTime=System.currentTimeMillis();
									
//									String basePath = request.getSession().getServletContext().getRealPath("/");
									String basePath = "E:\\images\\uploadApp\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path =timestamp+"_ml03"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
//									Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									lis.add(path);
									model.setDetails03("/imgApp/"+path);
									System.out.println(SystemParam.DOMAIN_NAME+model.getDetails03());
								
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							if (file.getName().equals("file04")) {
								String fileName = file.getOriginalFilename();
								if (fileName.trim() != "") {
									long  startTime=System.currentTimeMillis();
									
//									String basePath = request.getSession().getServletContext().getRealPath("/");
									String basePath = "E:\\images\\uploadApp\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path =timestamp+"_ml04"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
//									Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									lis.add(path);
									model.setDetails04("/imgApp/"+path);
									System.out.println(SystemParam.DOMAIN_NAME+model.getDetails04());
								
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							if (file.getName().equals("file05")) {
								String fileName = file.getOriginalFilename();
								if (fileName.trim() != "") {
									long  startTime=System.currentTimeMillis();
									
//									String basePath = request.getSession().getServletContext().getRealPath("/");
									String basePath = "E:\\images\\uploadApp\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path =timestamp+"_ml05"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
//									Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									lis.add(path);
									model.setDetails05("/imgApp/"+path);
									System.out.println(SystemParam.DOMAIN_NAME+model.getDetails05());
								
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							if (file.getName().equals("file06")) {
								String fileName = file.getOriginalFilename();
								if (fileName.trim() != "") {
									long  startTime=System.currentTimeMillis();
									
//									String basePath = request.getSession().getServletContext().getRealPath("/");
									String basePath = "E:\\images\\uploadApp\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path =timestamp+"_ml06"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
//									Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									lis.add(path);
									model.setDetails06("/imgApp/"+path);
									System.out.println(SystemParam.DOMAIN_NAME+model.getDetails06());
								
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							if (file.getName().equals("file07")) {
								String fileName = file.getOriginalFilename();
								if (fileName.trim() != "") {
									long  startTime=System.currentTimeMillis();
									
//									String basePath = request.getSession().getServletContext().getRealPath("/");
									String basePath = "E:\\images\\uploadApp\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path =timestamp+"_ml07"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
//									Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									lis.add(path);
									model.setDetails07("/imgApp/"+path);
									System.out.println(SystemParam.DOMAIN_NAME+model.getDetails07());
								
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							if (file.getName().equals("file08")) {
								String fileName = file.getOriginalFilename();
								if (fileName.trim() != "") {
									long  startTime=System.currentTimeMillis();
									
//									String basePath = request.getSession().getServletContext().getRealPath("/");
									String basePath = "E:\\images\\uploadApp\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path =timestamp+"_ml08"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
//									Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									lis.add(path);
									model.setDetails08("/imgApp/"+path);
									System.out.println(SystemParam.DOMAIN_NAME+model.getDetails08());
								
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							if (file.getName().equals("file09")) {
								String fileName = file.getOriginalFilename();
								if (fileName.trim() != "") {
									long  startTime=System.currentTimeMillis();
									
//									String basePath = request.getSession().getServletContext().getRealPath("/");
									String basePath = "E:\\images\\uploadApp\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path =timestamp+"_ml09"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
//									Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									lis.add(path);
									model.setDetails09("/imgApp/"+path);
									System.out.println(SystemParam.DOMAIN_NAME+model.getDetails09());
								
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							if (file.getName().equals("file10")) {
								String fileName = file.getOriginalFilename();
								if (fileName.trim() != "") {
									long  startTime=System.currentTimeMillis();
									
//									String basePath = request.getSession().getServletContext().getRealPath("/");
									String basePath = "E:\\images\\uploadApp\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path =timestamp+"_ml10"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
//									Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									lis.add(path);
									model.setDetails10("/imgApp/"+path);
									System.out.println(SystemParam.DOMAIN_NAME+model.getDetails10());
								
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							
					    }
				    }
					commodityDetailImgService.save(model);
					vn.setResult(Result.suc("商品详情图已添加!!"));
					// 详情图新增完毕
				}
					
			}else{
				vn.setResult(Result.fal("商品不存在!!"));
			}
			return vn;
	}
	/**
	 * 商品编辑
	 * @author CGS
	 * @time 2018年2月2日下午2:19:32
	 * @param model
	 * @param commodityId
	 * @param request
	 * @param f
	 * @return
	 * @throws IOException
	 * @throws ParseException 
	 */
	@RequestMapping(value="/updateCommoditySeckill.json",method={RequestMethod.POST})
	public @ResponseBody ResultVN updateCommodity(CommoditySeckill model,String seckillStartTime,String seckillEndTime,  MultipartHttpServletRequest request,file f) throws IOException, ParseException {
		ResultVN vn =new ResultVN();
		
		CommoditySeckill commoditySeckill =commoditySeckillService.get(model.getCommodityseckillId());
		if (CheckData.isNotNullOrEmpty(commoditySeckill)) {
		//本地
		List<String> lis=new ArrayList<String>();
		Iterator<String> iter = request.getFileNames();
		while (iter.hasNext()) {
			MultipartFile file = request.getFile(iter.next());
			if (file != null) {
				if (file.getName().equals("file1")) {
					String fileName = file.getOriginalFilename();
					if (fileName.trim() != "") {
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
						String timestamp = sdf.format(date);
//						String basePath = request.getSession().getServletContext().getRealPath("/");
						String basePath = "E:\\images\\uploadApp\\";
						fileName=fileName.substring(fileName.lastIndexOf("."));
						String path =timestamp+"_mlmiaosha"+fileName;// 文件保存路径
						File localFile = new File(basePath + path);
						file.transferTo(localFile);
//						Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
						new ImgPressThread(localFile).start();//多线程压缩图片
						lis.add(path);
						commoditySeckill.setSeckillCommodityimg("/imgApp/"+path);
					   
					}
				}
				
		}
	}
		commoditySeckill.setCommodityseckillName(model.getCommodityseckillName());//商品名称
		commoditySeckill.setSeckillPrice(Format.formatMoney(model.getSeckillPrice()));//商品普通价格
		commoditySeckill.setSeckillDiscountprice(Format.formatMoney(model.getSeckillDiscountprice()));//商品供销商价格
		commoditySeckill.setSeckillAmount(model.getSeckillAmount());//库存
		commoditySeckill.setSeckillAllowance(model.getSeckillAllowance());//总量
		commoditySeckill.setSeckillDescription(model.getSeckillDescription());//描述
		commoditySeckill.setSeckillSpecification(model.getSeckillSpecification());// 规格
		commoditySeckill.setSeckillOnsale(0);//商品状态0上架1下架
		commoditySeckill.setSeckillState(1);//商品秒杀状态0开启1关闭 —— 定时开启
		commoditySeckill.setSeckillVirtualSales(model.getSeckillVirtualSales());//虚拟销量
		commoditySeckill.setSeckillClassify(model.getSeckillClassify());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("startTime1:"+seckillStartTime);
		System.out.println("startTime2:"+sdf.parse(seckillStartTime));
		commoditySeckill.setStartTime(sdf.parse(seckillStartTime));
		System.out.println("endTime1:"+seckillEndTime);
		System.out.println("endTime2:"+sdf.parse(seckillEndTime));
		commoditySeckill.setEndTime(sdf.parse(seckillEndTime));
		commoditySeckillService.update(commoditySeckill);
		vn.setResult(Result.suc("商品修改成功!!"));
		return vn;
	}else{
		vn.setResult(Result.suc("商品不存在!!"));
		return vn;
	}
  }
	
	/**
	 * 秒杀商品详情图
	 * @author CGS
	 * @time 2018年5月22日下午4:34:43
	 * @param commodityId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/commoditySeckillDetailImg.json",method = {RequestMethod.POST})
	public @ResponseBody ResultObject<CommodityDetailImgVO> commodityDetailImg(Integer commodityseckillId) throws IOException {
			ResultObject<CommodityDetailImgVO> rs=new ResultObject<CommodityDetailImgVO>();
			CommodityDetailImgVO commodityDetailImgVO=commodityDetailImgService.findAdminByCommoditySeckillId(commodityseckillId);
			if (CheckData.allfieldIsNotNUll(commodityDetailImgVO)) {
				rs.setObject(commodityDetailImgVO);
				rs.setResult(Result.suc("查询成功!!"));
			}else{
				rs.setResult(Result.fal("暂无秒杀商品详情图!!"));
			}
			return rs;
	}
	
	/**
	 * 删除商品
	 * @author CGS
	 * @time 2018年2月2日下午2:20:17
	 * @param commodityId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/deleteCommoditySeckill.json")
	public @ResponseBody ResultVN deleteCommodity(Integer commodityseckillId) throws IOException {
		ResultVN vn =new ResultVN();
		commoditySeckillService.deleteByPrimaryKey(commodityseckillId);
		vn.setResult(Result.suc("商品删除成功!!"));
		return vn;
	}
	
	
	/**
	 * 秒杀活动改变
	 * @author CGS
	 * @time 2018年6月13日下午5:00:10
	 * @param commodityseckillId
	 * @param seckillState
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/closeAndStartCommoditySeckill.json")
	public @ResponseBody ResultVN closeAndStartCommoditySeckill(Integer commodityseckillId,Integer seckillState) throws IOException {
		ResultVN vn =new ResultVN();
		CommoditySeckill seckill=commoditySeckillService.get(commodityseckillId);
		if (CheckData.allfieldIsNotNUll(seckill) && CheckData.isNotNullOrEmpty(seckillState)) {
			if (seckillState ==1) {//商品秒杀状态0开启1关闭
				seckill.setSeckillOnsale(1);//商品状态0上架1下架
				seckill.setSeckillState(1);//商品秒杀状态0开启1关闭
				commoditySeckillService.update(seckill);
				vn.setResult(Result.suc("秒杀活动已关闭!!"));
			}else{
				seckill.setSeckillOnsale(0);//商品状态0上架1下架
				seckill.setSeckillState(1);//商品秒杀状态0开启1关闭  -----定时开启
				commoditySeckillService.update(seckill);
				vn.setResult(Result.suc("秒杀活动已开启!!"));
			}
		}else{
			vn.setResult(Result.fal("操作失败!!"));
		}
		return vn;
	}
}
