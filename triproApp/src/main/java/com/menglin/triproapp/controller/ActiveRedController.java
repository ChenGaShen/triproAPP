package com.menglin.triproapp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglin.triproapp.entity.ActiveRed;
import com.menglin.triproapp.entity.User;
import com.menglin.triproapp.service.IActiveRedService;
import com.menglin.triproapp.service.IUserService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.PageBean;
import com.menglin.triproapp.util.Result;
import com.menglin.triproapp.vo.PageRuslt;
import com.menglin.triproapp.vo.ResultVN;

/**
 * @author CGS
 * @date 2018年5月28日 下午4:10:09
 */
@Controller
@RequestMapping("/admin/activeRed")
public class ActiveRedController {

	@Resource
	private IActiveRedService activeRedService;
	
	@Resource  
    private IUserService userService;

	@RequestMapping(value = "/findByPage.json", method = { RequestMethod.POST })
	public @ResponseBody PageRuslt<ActiveRed> findByPage(Integer currentPage, Integer pageSize, ActiveRed model, HttpServletRequest request)
					throws UnsupportedEncodingException, IOException {
		// 页面数量 页码
		PageRuslt<ActiveRed> pageRuslt = new PageRuslt<ActiveRed>();
		PageBean<ActiveRed> PageActiveRed = activeRedService.findByPage(currentPage, pageSize, model);
		pageRuslt.setPageBean(PageActiveRed);
		return pageRuslt;

	}

	/**
	 * 用户单一红包发放
	 * 
	 * @author CGS
	 * @time 2018年5月28日下午4:08:41
	 * @param model
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/redEnvelope.json", method = { RequestMethod.POST })
	public @ResponseBody ResultVN redEnvelope(ActiveRed model, Integer uid) {
		ResultVN vn = new ResultVN();
		User user = userService.get(uid);
		if (CheckData.isNotNullOrEmpty(user.getUserId())) {
			model.setRedState(0);// 0 未使用1 已使用 2已过期
			model.setUid(uid);
			activeRedService.save(model);
			vn.setResult(Result.suc("红包发放成功!!"));
		} else {
			vn.setResult(Result.fal("发放失败!!"));
		}
		return vn;
	}
	
	/**
	 * 批量发送红包
	 * @author CGS
	 * @time 2018年5月30日下午2:57:47
	 * @param model
	 * @param uids
	 * @return
	 */
	@RequestMapping(value = "/batchRedEnvelope.json", method = { RequestMethod.POST })
	public @ResponseBody ResultVN batchRedEnvelope(ActiveRed model, String ids) {
		ResultVN vn = new ResultVN();
		String [] stringId=ids.split(",");
		if (stringId.length>0) {
			for (int i = 0; i < stringId.length; i++) {
				User user = userService.get(Integer.valueOf(stringId[i]));
				if (CheckData.isNotNullOrEmpty(user.getUserId())) {
					model.setRedState(0);// 0 未使用1 已使用 2已过期
					model.setUid(user.getUserId());
					model.setAddTime(new Date());
					activeRedService.save(model);
					//消息添加
				}
		}
			vn.setResult(Result.suc("批量红包发放成功!!"));
			} else {
				vn.setResult(Result.fal("发放失败!!"));
			}
		return vn;
	}
}
