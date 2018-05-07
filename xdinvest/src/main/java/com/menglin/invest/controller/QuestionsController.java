package com.menglin.invest.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.menglin.invest.entity.Questions;
import com.menglin.invest.service.impl.QuestionsService;
import com.menglin.invest.util.CheckData;
import com.menglin.invest.util.PageBean;
import com.menglin.invest.util.Result;
import com.menglin.invest.vo.PageRuslt;
import com.menglin.invest.vo.ResultVN;

@Controller  
@RequestMapping("/xdadmin/questions")
public class QuestionsController {
	@Autowired
	private QuestionsService questionsService;
	
	/**
	 * 常见问题分页查询 正式
	 * @author CGS
	 * @time 2018年3月14日上午9:40:08
	 * @param currentPage
	 * @param pageSize
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/findByPage.json",method={RequestMethod.POST})  
    public @ResponseBody PageRuslt<Questions> findByPage(Integer currentPage, Integer pageSize,Questions model,String startTime,String endTime,HttpServletRequest request){
		PageRuslt<Questions> pageRuslt =new PageRuslt<Questions>();
		PageBean<Questions> PageQuestions=questionsService.findByPage(currentPage, pageSize, model,startTime,endTime);
		pageRuslt.setPageBean(PageQuestions);
      return pageRuslt;
       
	}
	
	/**
	 * 新增问题
	 * @author CGS
	 * @time 2018年4月18日下午2:30:33
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/doAdd.json",method={RequestMethod.POST})
	public @ResponseBody ResultVN doAdd(Questions model,HttpServletRequest request){
		ResultVN vn= new ResultVN();
			if (CheckData.isNotNullOrEmpty(model)) {
				model.setUpdateTime(new Date());
				questionsService.save(model);
				vn.setResult(Result.suc("新增成功!!"));
			}else{
				vn.setResult(Result.fal("新增失败!!"));
			}
      return vn;
       
	}
	
	/**
	 * 常见问题更新
	 * @author CGS
	 * @time 2018年4月18日下午3:57:54
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/doUpdate.json",method={RequestMethod.POST})
	public @ResponseBody ResultVN doUpdate(Questions model,HttpServletRequest request){
		ResultVN vn= new ResultVN();
		Questions questions =questionsService.get(model.getQuestionId());
			if (CheckData.isNotNullOrEmpty(questions)) {
				if (CheckData.isNotNullOrEmpty(model.getQuestionClassification())) {
					questions.setQuestionClassification(model.getQuestionClassification());
				}
				if (CheckData.isNotNullOrEmpty(model.getQuestionContent())) {
					questions.setQuestionContent(model.getQuestionContent());
				}
				if (CheckData.isNotNullOrEmpty(model.getQuestionAnswer())) {
					questions.setQuestionAnswer(model.getQuestionAnswer());
				}
				if (CheckData.isNotNullOrEmpty(model.getQuestionState())) {
					questions.setQuestionState(model.getQuestionState());
				}
				questions.setUpdateTime(new Date());
				questionsService.update(questions);
				vn.setResult(Result.suc("更新成功!!"));
			}else{
				vn.setResult(Result.fal("更新失败!!"));
			}
      return vn;
       
	}
	
	
	/**
	 * 删除问题
	 * @author CGS
	 * @time 2018年4月18日下午2:33:18
	 * @param newsId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/doDelete.json",method={RequestMethod.POST})
	public @ResponseBody ResultVN doDelete(Integer questionId,HttpServletRequest request){
		ResultVN vn= new ResultVN();
			if (CheckData.isNotNullOrEmpty(questionId)) {
				questionsService.delete(questionId);
				vn.setResult(Result.suc("删除成功!!"));
			}else{
				vn.setResult(Result.fal("删除失败!!"));
			}
      return vn;
       
	}
}
