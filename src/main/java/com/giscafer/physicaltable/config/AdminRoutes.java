package com.giscafer.physicaltable.config;

import com.giscafer.physicaltable.controller.CacheController;
import com.giscafer.physicaltable.controller.ReportcardController;
import com.giscafer.physicaltable.controller.UserController;
import com.jfinal.config.Routes;

/**
 * 后端路由规则
 * @author giscafer
 */
public class AdminRoutes extends Routes {

	@Override
	public void config() {
		add("/reportcard",ReportcardController.class,"/reportcard");
		add("/cache",CacheController.class,"/");
	}

}
