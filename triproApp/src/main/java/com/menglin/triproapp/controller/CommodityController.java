package com.menglin.triproapp.controller;

import java.io.File;

import java.io.IOException;
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
import com.menglin.triproapp.service.ICommodityDetailImgService;
import com.menglin.triproapp.service.ICommodityService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.Format;
import com.menglin.triproapp.util.ImgPressThread;
import com.menglin.triproapp.util.PageBean;
import com.menglin.triproapp.util.Result;
import com.menglin.triproapp.util.SystemParam;
import com.menglin.triproapp.util.file;
import com.menglin.triproapp.vo.CommodityDetailVO;
import com.menglin.triproapp.vo.PageRuslt;
import com.menglin.triproapp.vo.ResultVN;


/** 
 * @author CGS 
 * @date 2018年2月1日 下午1:33:48 
 */
@Controller  
@RequestMapping("/admin/commodity")
public class CommodityController {
	
	@Resource  
    private ICommodityService commodityService;
	
	@Resource  
    private ICommodityDetailImgService  commodityDetailImgService;
	
	
	
	
	/**
	 * 商品列表分页
	 * @author CGS
	 * @time 2018年3月22日下午1:43:31
	 * @param currentPage
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findByPage.json",method={RequestMethod.POST})  
    public @ResponseBody PageRuslt<CommodityDetailVO> findByPage(int currentPage, int pageSize,Commodity model){
		PageRuslt<CommodityDetailVO> pageRuslt =new PageRuslt<CommodityDetailVO>();
		PageBean<CommodityDetailVO> PageUser=commodityService.findByPage(currentPage, pageSize, model);
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
	@RequestMapping(value="/commodityDetail.json",method={RequestMethod.POST})  
	public @ResponseBody CommodityDetailVO commodityDetail(Integer commodityId){
		Commodity commodity =commodityService.get(commodityId);
		CommodityDetailVO vo=new CommodityDetailVO();
		if (null!=commodity) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			vo.setCommodityId(commodity.getCommodityid());
			vo.setCommodityName(commodity.getCommodityName());
			vo.setPrice(Format.keepTwoMoney(commodity.getPrice()));
			vo.setDiscountPrice(Format.keepTwoMoney(commodity.getDiscountPrice()));
			vo.setAmount(commodity.getAmount());
			vo.setAllowance(commodity.getAllowance());
			if (null!=commodity.getCommodityImg()) {
				vo.setCommodityImg(SystemParam.DOMAIN_NAME+commodity.getCommodityImg());
			}
			vo.setSpecification(commodity.getSpecification());
			vo.setRealSale(commodity.getRealSale());
			vo.setVirtualSales(commodity.getVirtualSales());
			vo.setState(commodity.getState());
			vo.setAddTime(sdf.format(commodity.getAddTime()));
			vo.setUpdateTime(sdf.format(commodity.getUpdateTime()));
			vo.setDescription(commodity.getDescription());
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
	 */
	@RequestMapping(value="/addCommodity.json",method={RequestMethod.POST})
	public @ResponseBody ResultVN addCommodity(Commodity model, MultipartHttpServletRequest request,file f) throws IOException {
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
								long  startTime=System.currentTimeMillis();
								Date date = new Date();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
								String timestamp = sdf.format(date);
//								String basePath = request.getSession().getServletContext().getRealPath("/");
								String basePath = "E:\\images\\uploadApp\\";
								fileName=fileName.substring(fileName.lastIndexOf("."));
								String path =timestamp+"_ml"+fileName;// 文件保存路径
								File localFile = new File(basePath + path);
								file.transferTo(localFile);
//								Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
								new ImgPressThread(localFile).start();//多线程压缩图片
								lis.add(path);
								model.setCommodityImg("/imgApp/"+path);
								System.out.println(SystemParam.DOMAIN_NAME+model.getCommodityImg());
							
								long  endTime=System.currentTimeMillis();
								System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
							   
							}
						}
						
				}
					//测试+正式
					/*List<String> lis=new ArrayList<String>();
					Iterator<String> iter = request.getFileNames();
					while (iter.hasNext()) {
						MultipartFile file = request.getFile(iter.next());
						if (file != null) {
							if (file.getName().equals("file")) {
								String fileName = file.getOriginalFilename();
								if (fileName.trim() != "") {
									long  startTime=System.currentTimeMillis();
									Date date = new Date();
									SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
									String timestamp = sdf.format(date);
									String basePath = request.getSession().getServletContext().getRealPath("/");
//									String basePath = "E:\\images\\upload\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path ="/static/upload/images/"+timestamp+"_ml"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
//									Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									lis.add(path);
									model.setImg(path);
									System.out.println(SystemParam.DOMAIN_NAME+model.getImg());
								
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							
					}*/
		
	/*	if (!f.getFile().getOriginalFilename().equals("")) {
			String fileName = f.getFile().getOriginalFilename();
			String imgType=fileName.substring(fileName.indexOf("."));
			String basePath = request.getSession().getServletContext().getRealPath("/")+"/static/upload/images/";
			if (fileName.trim() != "") {
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String timestamp = sdf.format(date);
				String newName=timestamp+imgType;
				String path =  newName;// 文件保存路径
				OutputStream output = new FileOutputStream(new File(basePath, newName));//在路径URL中创建newFileName文件
				IOUtils.copy(f.getFile().getInputStream(), output);//将file中的图片写入
				File localFile = new File(basePath + path);
				Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
//				ImgCompress imgCom = new ImgCompress(basePath + newName);  
//				imgCom.resizeFix(400, 400,basePath + newName);
				model.setImg(path); 
				System.out.println(model.getImg().toString());
				
				
			
			}
		}	*/
					
	 }			model.setCommodityName(model.getCommodityName());//商品名称
				model.setPrice(Format.formatMoney(model.getPrice()));//商品普通价格
				model.setDiscountPrice(Format.formatMoney(model.getDiscountPrice()));//商品供销商价格
				model.setAllowance(model.getAmount());//库存
				model.setAmount(model.getAmount());//总量
				model.setDescription(model.getDescription());//描述
				model.setSpecification(model.getSpecification());// 规格
				model.setState(1);//商品状态1上架0下架
				model.setRealSale(0);//实际销量
				model.setVirtualSales(model.getVirtualSales());//虚拟销量
				model.setClassify(model.getClassify());
				model.setAddTime(new Date());
				model.setUpdateTime(new Date());
				commodityService.save(model);
				vn.setResult(Result.suc("商品添加成功!!"));
				System.out.println("添加名称："+model.getCommodityName());
				System.out.println("添加规格："+model.getSpecification());
				System.out.println("添加描述："+model.getDescription());
				
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
	@RequestMapping(value="/addCommodityDetailImg.json",method={RequestMethod.POST})
	public @ResponseBody ResultVN addCommodityDetailImg(CommodityDetails model, MultipartHttpServletRequest request,file f) throws IOException {
			ResultVN vn =new ResultVN();
			Commodity commodity =commodityService.get(model.getCommodityId());
			if (CheckData.isNotNullOrEmpty(commodity)) {
				List<String> lis=new ArrayList<String>();
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String timestamp = sdf.format(date);
				//区分新增还是更新？
				CommodityDetails commodityDetails =commodityDetailImgService.get(model.getDetailsId());
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
	 */
	@RequestMapping(value="/updateCommodity.json",method={RequestMethod.POST})
	public @ResponseBody ResultVN updateCommodity(Commodity model, MultipartHttpServletRequest request,file f) throws IOException {
		ResultVN vn =new ResultVN();
		
		Commodity commodity =commodityService.get(model.getCommodityid());
		if (null!=commodity) {
		//本地
		List<String> lis=new ArrayList<String>();
		Iterator<String> iter = request.getFileNames();
		while (iter.hasNext()) {
			MultipartFile file = request.getFile(iter.next());
			if (file != null) {
				if (file.getName().equals("file1")) {
					String fileName = file.getOriginalFilename();
					if (fileName.trim() != "") {
						long  startTime=System.currentTimeMillis();
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
						String timestamp = sdf.format(date);
//						String basePath = request.getSession().getServletContext().getRealPath("/");
						String basePath = "E:\\images\\uploadApp\\";
						fileName=fileName.substring(fileName.lastIndexOf("."));
						String path =timestamp+"_ml"+fileName;// 文件保存路径
						File localFile = new File(basePath + path);
						file.transferTo(localFile);
//						Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
						new ImgPressThread(localFile).start();//多线程压缩图片
						lis.add(path);
						commodity.setCommodityImg("/imgApp/"+path);
						System.out.println(SystemParam.DOMAIN_NAME+commodity.getCommodityImg());
					
						long  endTime=System.currentTimeMillis();
						System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
					   
					}
				}
				
		}
	}
	
		commodity.setCommodityName(model.getCommodityName());//商品名称
		commodity.setPrice(Format.formatMoney(model.getPrice()));//商品普通价格
		commodity.setDiscountPrice(Format.formatMoney(model.getDiscountPrice()));//商品供销商价格
		commodity.setAllowance(model.getAmount());//库存
		commodity.setAmount(model.getAmount());//总量
		commodity.setDescription(model.getDescription());//描述
		commodity.setSpecification(model.getSpecification());// 规格
		commodity.setState(1);//商品状态1上架0下架
		commodity.setClassify(model.getClassify());
		commodity.setVirtualSales(model.getVirtualSales());//虚拟销量
		commodity.setUpdateTime(new Date());
		commodityService.update(commodity);
		vn.setResult(Result.suc("商品修改成功!!"));
		System.out.println("修改名称："+model.getCommodityName());
		System.out.println("修改规格："+model.getSpecification());
		System.out.println("修改描述："+model.getDescription());
		return vn;
	}else{
		vn.setResult(Result.suc("商品不存在!!"));
		return vn;
	}
  }
	/**
	 * 删除商品
	 * @author CGS
	 * @time 2018年2月2日下午2:20:17
	 * @param commodityId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/deleteCommodity.json")
	public @ResponseBody ResultVN deleteCommodity(Integer commodityId) throws IOException {
		ResultVN vn =new ResultVN();
		commodityService.deleteByPrimaryKey(commodityId);
		vn.setResult(Result.suc("商品删除成功!!"));
		return vn;
	}
	
	/**
	 * 下架商品
	 * @author CGS
	 * @time 2018年2月2日下午2:24:52
	 * @param commodityId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/soldOutCommodity.json")
	public @ResponseBody ResultVN soldOutCommodity(Integer[] commodityIds) throws IOException {
		ResultVN vn =new ResultVN();
		commodityService.soldOutCommodity(commodityIds);
		vn.setResult(Result.suc("商品下架成功!!"));
		return vn;
	}
}
