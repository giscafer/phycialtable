package com.giscafer.phycialtable.config;

import com.giscafer.phycialtable.controller.IndexController;
import com.giscafer.phycialtable.controller.RegisterController;
import com.jfinal.config.Routes;

/**
 * 前端路由规则
 * @author netbuffer
 */
public class FrontRoutes extends Routes {

	@Override
	public void config() {
		add("/", IndexController.class, "/");
		add("/register", RegisterController.class, "/");
	}

}
