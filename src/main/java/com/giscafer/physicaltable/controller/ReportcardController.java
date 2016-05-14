package com.giscafer.physicaltable.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.giscafer.physicaltable.interceptor.TimeInterceptor;
import com.giscafer.physicaltable.model.User;
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
		render("main.html");
	}
	
	/**
	 * 开启缓存
	 */
	@Before({CacheInterceptor.class})
	public void reportlist() {
		int limit=getParaToInt("limit");
		int offset=getParaToInt("offset");
		Map<String, Object> data=reportservice.getReportList(offset, limit);
		if(data!=null && data.size()>0){
			renderJson(data);
		}else{
			renderJson();
		}
		
	}
	public void delete(){
		User.dao.deleteById(getPara("id"));
		setAttr("status", "success");
		renderJson();
	}
	public void add(){
		render("addForm.html");
	}
}
