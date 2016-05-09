package com.giscafer.phycialtable.config;

import com.giscafer.phycialtable.controller.CacheController;
import com.giscafer.phycialtable.controller.UserController;
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
