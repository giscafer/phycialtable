package com.giscafer.physicaltable.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.giscafer.physicaltable.Constant.ConfigConstant;
import com.giscafer.physicaltable.interceptor.TimeInterceptor;
import com.giscafer.physicaltable.model.Reportcard;
import com.giscafer.physicaltable.model.User;
import com.giscafer.physicaltable.model.base.BaseReportcard;
import com.giscafer.physicaltable.service.IReportcardService;
import com.giscafer.physicaltable.service.impl.ReportcardServiceImpl;
import com.jfinal.aop.Before;
import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import com.jfinal.plugin.ehcache.CacheInterceptor;

/**
 * report控制器
 * @author giscafer
 *
 */
@Before({ TimeInterceptor.class })
public class ReportcardController extends Controller {
	private static IReportcardService reportservice=Enhancer.enhance(ReportcardServiceImpl.class);
	private static Logger reportLog= LoggerFactory
			.getLogger(ReportcardController.class);

	public void index() {
		Object username=getSession().getAttribute(ConfigConstant.USERNAME);
		setAttr("username",username);
		render("main.html");
	}
	
	/**
	 * 开启缓存
	 */
//	@Before({CacheInterceptor.class})
	public void reportlist() {
		int limit=getParaToInt("limit");
		int offset=getParaToInt("offset");
		String studentId=getPara("search");
		Map<String, Object> data=reportservice.getReportList(offset, limit,studentId);
		if(data!=null && data.size()>0){
			renderJson(data);
		}else{
			renderJson();
		}
		
	}
	public void delete(){
		boolean result=Reportcard.dao.deleteById(getPara("id"));
		setAttr("status", "success");
		renderJson();
	}
	public void addForm(){
		render("addForm.html");
	}
	public void save(){
		Reportcard report=getModel(Reportcard.class);
		if(report.getId()!=null){
			report.update();
		}else{
			report.save();
		}
		redirect("/reportcard");
	}
	public void setEditData(){
		getSession().setAttribute("reportcard", Reportcard.dao.findById(getPara("id")));
		renderJson();
	}
	public void editForm(){
		setAttr("reportcard",getSession().getAttribute("reportcard"));
		System.out.println(getAttr("reportcard"));
		render("editForm.html");
	}
	public void newdata(){
		int count=reportservice.getNewData();
		renderJson(count);
	}
}
