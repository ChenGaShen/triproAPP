package com.menglin.tripro.controller;

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

import com.menglin.tripro.entity.Admin;
import com.menglin.tripro.entity.Commodity;
import com.menglin.tripro.service.ICommodityService;
import com.menglin.tripro.util.CheckData;
import com.menglin.tripro.util.Format;
import com.menglin.tripro.util.ImgPressThread;
import com.menglin.tripro.util.PageBean;
import com.menglin.tripro.util.Result;
import com.menglin.tripro.util.SystemParam;
import com.menglin.tripro.util.file;
import com.menglin.tripro.vo.CommodityDetailVO;
import com.menglin.tripro.vo.PageRuslt;
import com.menglin.tripro.vo.ResultVN;


/** 
 * @author CGS 
 * @date 2018年2月1日 下午1:33:48 
 */
@Controller  
@RequestMapping("/admin/commodity")
public class CommodityController {
	
	@Resource  
    private ICommodityService commodityService;
	
	
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
			vo.setCommodityId(commodity.getId());
			vo.setName(commodity.getName());
			vo.setPrice(Format.keepTwoMoney(commodity.getPrice()));
			vo.setDiscountPrice(Format.keepTwoMoney(commodity.getDiscountPrice()));
			vo.setAmount(commodity.getAmount());
			vo.setAllowance(commodity.getAllowance());
			if (null!=commodity.getImg()) {
				vo.setImg(SystemParam.DOMAIN_NAME+commodity.getImg());
			}
			vo.setSpecification(commodity.getSpecification());
			vo.setSales(commodity.getSales());
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
								String basePath = "E:\\images\\upload\\";
								fileName=fileName.substring(fileName.lastIndexOf("."));
								String path =timestamp+"_ml"+fileName;// 文件保存路径
								File localFile = new File(basePath + path);
								file.transferTo(localFile);
//								Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
								new ImgPressThread(localFile).start();//多线程压缩图片
								lis.add(path);
								model.setImg("/img/"+path);
								System.out.println(SystemParam.DOMAIN_NAME+model.getImg());
							
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
					
	 }			model.setName(model.getName());//商品名称
				model.setPrice(Format.formatMoney(model.getPrice()));//商品普通价格
				model.setDiscountPrice(Format.formatMoney(model.getDiscountPrice()));//商品供销商价格
				model.setAllowance(model.getAmount());//库存
				model.setAmount(model.getAmount());//总量
				model.setDescription(model.getDescription());//描述
				model.setSpecification(model.getSpecification());// 规格
				model.setState(1);//商品状态1上架0下架
				model.setSales(0);//销量
				model.setAddTime(new Date());
				model.setUpdateTime(new Date());
				commodityService.save(model);
				vn.setResult(Result.suc("商品添加成功!!"));
				System.out.println("添加名称："+model.getName());
				System.out.println("添加规格："+model.getSpecification());
				System.out.println("添加描述："+model.getDescription());
				
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
		
		Commodity commodity =commodityService.get(model.getId());
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
						String basePath = "E:\\images\\upload\\";
						fileName=fileName.substring(fileName.lastIndexOf("."));
						String path =timestamp+"_ml"+fileName;// 文件保存路径
						File localFile = new File(basePath + path);
						file.transferTo(localFile);
//						Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
						new ImgPressThread(localFile).start();//多线程压缩图片
						lis.add(path);
						commodity.setImg("/img/"+path);
						System.out.println(SystemParam.DOMAIN_NAME+commodity.getImg());
					
						long  endTime=System.currentTimeMillis();
						System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
					   
					}
				}
				
		}
	}
	
		commodity.setName(model.getName());//商品名称
		commodity.setPrice(Format.formatMoney(model.getPrice()));//商品普通价格
		commodity.setDiscountPrice(Format.formatMoney(model.getDiscountPrice()));//商品供销商价格
		commodity.setAllowance(model.getAmount());//库存
		commodity.setAmount(model.getAmount());//总量
		commodity.setDescription(model.getDescription());//描述
		commodity.setSpecification(model.getSpecification());// 规格
		commodity.setState(1);//商品状态1上架0下架
		commodity.setSales(commodity.getSales());//销量
		commodity.setUpdateTime(new Date());
		commodityService.update(commodity);
		vn.setResult(Result.suc("商品修改成功!!"));
		System.out.println("修改名称："+model.getName());
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
