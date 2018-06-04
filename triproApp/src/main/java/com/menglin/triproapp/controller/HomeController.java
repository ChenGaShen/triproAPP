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
import com.menglin.triproapp.entity.Home;
import com.menglin.triproapp.service.ICommodityDetailImgService;
import com.menglin.triproapp.service.ICommodityService;
import com.menglin.triproapp.service.IHomeService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.Format;
import com.menglin.triproapp.util.ImgPressThread;
import com.menglin.triproapp.util.PageBean;
import com.menglin.triproapp.util.Result;
import com.menglin.triproapp.util.SystemParam;
import com.menglin.triproapp.util.file;

import com.menglin.triproapp.vo.PageRuslt;
import com.menglin.triproapp.vo.ResultVN;


/** 
 * @author CGS 
 * @date 2018年2月1日 下午1:33:48 
 */
@Controller  
@RequestMapping("/admin/home")
public class HomeController {
	
	@Resource  
    private IHomeService homeService;
	
	
	/**
	 * 首页广告分页
	 * @author CGS
	 * @time 2018年5月25日上午10:36:29
	 * @param currentPage
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findByPage.json",method={RequestMethod.POST})  
    public @ResponseBody PageRuslt<Home> findByPage(int currentPage, int pageSize,Home model){
		PageRuslt<Home> pageRuslt =new PageRuslt<Home>();
		PageBean<Home> PageUser=homeService.findByPage(currentPage, pageSize, model);
		pageRuslt.setPageBean(PageUser);
      return pageRuslt;
       
	}
	
	/**
	 * 首页图片详情
	 * @author CGS
	 * @time 2018年5月25日上午10:58:10
	 * @param homeId
	 * @return
	 */
	@RequestMapping(value="/homeDetail.json",method={RequestMethod.POST})  
	public @ResponseBody Home homeDetail(Integer homeId){
		Home home =homeService.get(homeId);
		if (CheckData.isNotNullOrEmpty(home)) {
			if (CheckData.isNotNullOrEmpty(home.getImg())) {
				home.setImg(SystemParam.DOMAIN_NAME+home.getImg());
			}
			home.setAddTime(home.getAddTime());
			home.setUpdateTime(home.getUpdateTime());
		}
		return home;
	}
	
	
	/**
	 * 首页图片添加
	 * @author CGS
	 * @time 2018年5月25日上午10:51:01
	 * @param model
	 * @param request
	 * @param f
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/addHomeImg.json",method={RequestMethod.POST})
	public @ResponseBody ResultVN addHomeImg(Home model, MultipartHttpServletRequest request,file f) throws IOException {
		ResultVN vn =new ResultVN();
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
								String basePath = "E:\\images\\uploadApp\\";
								fileName=fileName.substring(fileName.lastIndexOf("."));
								String path =timestamp+"_ml"+fileName;// 文件保存路径
								File localFile = new File(basePath + path);
								file.transferTo(localFile);
								new ImgPressThread(localFile).start();//多线程压缩图片
								model.setImg("/imgApp/"+path);
								System.out.println(SystemParam.DOMAIN_NAME+model.getImg());
								long  endTime=System.currentTimeMillis();
								System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
							   
							}
						}
						
				}
			}	
				model.setAddTime(new Date());
				model.setUpdateTime(new Date());
				homeService.save(model);
				vn.setResult(Result.suc("添加成功!!"));
				return vn;
  }
	
	
	/**
	 * 修改首页图片
	 * @author CGS
	 * @time 2018年5月25日上午10:57:44
	 * @param model
	 * @param request
	 * @param f
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/updateHomeImg.json",method={RequestMethod.POST})
	public @ResponseBody ResultVN updateHomeImg(Home model, MultipartHttpServletRequest request,file f) throws IOException {
				ResultVN vn =new ResultVN();
				Home home = homeService.get(model.getHomeId());
				if (CheckData.isNotNullOrEmpty(home)) {
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
									String basePath = "E:\\images\\uploadApp\\";
									fileName=fileName.substring(fileName.lastIndexOf("."));
									String path =timestamp+"_ml"+fileName;// 文件保存路径
									File localFile = new File(basePath + path);
									file.transferTo(localFile);
									new ImgPressThread(localFile).start();//多线程压缩图片
									home.setImg("/imgApp/"+path);
									System.out.println(SystemParam.DOMAIN_NAME+model.getImg());
									long  endTime=System.currentTimeMillis();
									System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
								   
								}
							}
							
					}
				}
					
					home.setState(model.getState());
					home.setType(model.getType());
					home.setDescription(model.getDescription());
					home.setUrl(model.getUrl());
					model.setUpdateTime(new Date());
					homeService.update(home);
					vn.setResult(Result.suc("修改成功!!"));
			}else{
				vn.setResult(Result.fal("操作失败!!"));
			}
				
				return vn;
  }
	/**
	 * 删除首页广告图片
	 * @author CGS
	 * @time 2018年5月25日上午10:57:14
	 * @param homeId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/deleteHomeImg.json")
	public @ResponseBody ResultVN deleteHomeImg(Integer homeId) throws IOException {
		ResultVN vn =new ResultVN();
		homeService.deleteByPrimaryKey(homeId);
		vn.setResult(Result.suc("删除成功!!"));
		return vn;
	}
	
	
}
