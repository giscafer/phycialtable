package com.giscafer.physicaltable.config;

import com.giscafer.physicaltable.controller.IndexController;
import com.giscafer.physicaltable.controller.RegisterController;
import com.jfinal.config.Routes;

/**
 * 前端路由规则
 * @author giscafer
 */
public class FrontRoutes extends Routes {

	@Override
	public void config() {
		add("/", IndexController.class, "/");
		add("/register", RegisterController.class, "/");
	}

}
