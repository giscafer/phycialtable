package com.giscafer.physicaltable.config;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.druid.wall.WallFilter;
import com.giscafer.physicaltable.interceptor.AuthInterceptor;
import com.giscafer.physicaltable.model._MappingKit;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.ModelRecordElResolver;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.druid.IDruidStatViewAuth;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;

/**
 * jfinal
 * 
 */
public class AppConfig extends JFinalConfig {

	/**
	 * 
	 */
	public void afterJFinalStart() {
		ModelRecordElResolver.setResolveBeanAsModel(true);
	}

	/**
	 * 
	 */
	public void beforeJFinalStop() {
		System.out.println("jfinal鍋滄!");
	};

	/**
	 */
	@Override
	public void configConstant(Constants me) {
		Prop p = PropKit.use("config.txt");
		me.setDevMode(p.getBoolean("devMode"));
		me.setEncoding("utf8");
		/**
		 * 
		 */
		me.setBaseUploadPath("upload");
		me.setBaseDownloadPath("export");
		me.setViewType(ViewType.OTHER);
		me.setError403View("/403.html");
		me.setError404View("/404.html");
		me.setError500View("/500.html");
	}

	@Override
	public void configRoute(Routes me) {
		me.add(new FrontRoutes()); 
		me.add(new AdminRoutes());
	}

	/**
	 * 
	 */
	@Override
	public void configPlugin(Plugins me) {
		loadPropertyFile("config.txt");
		DruidPlugin druid = new DruidPlugin(getProperty("jdbcUrl"),
				getProperty("user"), getProperty("password"));
		druid.setDriverClass(getProperty("driver"));
		druid.addFilter(new StatFilter());
		WallFilter wall = new WallFilter();
		wall.setDbType(JdbcConstants.MYSQL);
		wall.setLogViolation(true);
		druid.addFilter(wall);
		me.add(druid);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druid);
		arp.setDevMode(true);
		arp.setShowSql(true);
		arp.setContainerFactory(new CaseInsensitiveContainerFactory(true));
		_MappingKit.mapping(arp);
		me.add(arp);
		// arp.addMapping("user", User.class);
		//EhCachePlugin 
		me.add(new EhCachePlugin());
	}
	
	@Override
	public void configInterceptor(Interceptors me) {
		me.addGlobalActionInterceptor(new AuthInterceptor());

	}

	/**
	 * 
	 */
	@Override
	public void configHandler(Handlers me) {
		// me.add(new ResourceHandler());
		DruidStatViewHandler dvh = new DruidStatViewHandler("/druid",
				new IDruidStatViewAuth() {
					public boolean isPermitted(HttpServletRequest request) {
						// HttpSession hs = request.getSession(false);
						// return (hs != null && hs.getAttribute("admin") !=
						// null);
						return true;
					}
				});
		me.add(dvh);
	}
}
