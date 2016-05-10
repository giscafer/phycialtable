package com.giscafer.physicaltable.config;

import com.giscafer.physicaltable.controller.CacheController;
import com.giscafer.physicaltable.controller.UserController;
import com.jfinal.config.Routes;

/**
 * 后端路由规则
 * @author champ
 */
public class AdminRoutes extends Routes {

	@Override
	public void config() {
		add("/user",UserController.class,"/");
		add("/cache",CacheController.class,"/");
	}

}
