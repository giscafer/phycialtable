package com.giscafer.phycialtable.config;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.druid.wall.WallFilter;
import com.giscafer.phycialtable.interceptor.AuthInterceptor;
import com.giscafer.phycialtable.model._MappingKit;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PathKit;
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
 * jfinal閰嶇疆鏂囦欢
 * 
 * @author champ
 *
 */
public class AppConfig extends JFinalConfig {

	/**
	 * 鍚姩瀹屽洖璋�
	 */
	public void afterJFinalStart() {
		/**
		 * 娣诲姞setResolveBeanAsModel(boolean)
		 * 锛岃缃负true鏃讹紝鐢ㄤ簬鎸囧畾鍦↗SP/jstl涓紝瀵瑰緟鍚堜綋鍚庣殑Bean浠嶇劧閲囩敤鑰佺増鏈寰匨odel鐨勬柟寮忚緭鍑烘暟鎹紝涔熷嵆浣跨敤
		 * Model.get(String attr)鑰岄潪Bean鐨刧etter鏂规硶杈撳嚭鏁版嵁锛屾湁鍒╀簬鍦ㄥ叧鑱旀煡璇㈡椂杈撳嚭鏃�getter
		 * 鏂规硶鐨勫瓧娈靛�銆傚缓璁甿ysql鏁版嵁琛ㄤ腑鐨勫瓧娈甸噰鐢ㄩ┘宄板懡鍚嶏紝琛ㄥ悕閲囩敤涓嬪垝绾挎柟寮忓懡鍚嶄究浜巜in涓巐inux闂寸Щ妞�
		 */
		ModelRecordElResolver.setResolveBeanAsModel(true);
	}

	/**
	 * 鍋滄鍥炶皟
	 */
	public void beforeJFinalStop() {
		System.out.println("jfinal鍋滄!");
	};

	/**
	 * 甯搁噺閰嶇疆
	 */
	@Override
	public void configConstant(Constants me) {
		Prop p = PropKit.use("config.txt");
		me.setDevMode(p.getBoolean("devMode"));// 寮�彂妯″紡
		me.setEncoding("utf8");
		/**
		 * 璇ヨ矾寰勫弬鏁版帴鍙椾互鈥�鈥濇墦澶存垨鑰呬互 windows 纾佺洏鐩樼鎵撳ご鐨勭粷瀵硅矾寰勶紝
		 * 鍗冲彲灏嗗熀纭�矾寰勬寚鍚戦」鐩牴寰勪箣澶栵紝鏂逛究鍗曟満澶氬疄渚嬮儴缃层�褰撹璺緞鍙傛暟璁剧疆涓虹浉瀵硅矾寰勬椂锛�鍒欐槸浠ラ」鐩牴涓哄熀纭�殑鐩稿璺緞
		 */
		me.setBaseUploadPath("upload");
		me.setBaseDownloadPath("export");
		me.setViewType(ViewType.OTHER);
		me.setError403View("/403.html");
		// 404閿欒鏄痺eb搴旂敤鎶ュ嚭鐨勶紝鍙兘渚濋潬web.xml閲岄潰鏉ラ厤缃�
		me.setError404View("/404.html");
		me.setError500View("/500.html");
	}

	@Override
	public void configRoute(Routes me) {
		// me.add("/", IndexController.class, "/"); // 绗笁涓弬鏁颁负璇ontroller鐨勮鍥惧瓨鏀捐矾寰�
		// me.add("/blog", BlogController.class); // 绗笁涓弬鏁扮渷鐣ユ椂榛樿涓庣涓�釜鍙傛暟鍊肩浉鍚岋紝鍦ㄦ鍗充负
		// "/blog"
		me.add(new FrontRoutes()); // 鍓嶇璺敱
		me.add(new AdminRoutes()); // 鍚庣璺敱
	}

	/**
	 * 鎻掍欢閰嶇疆
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
		// 璁剧疆鏁版嵁搴撳ぇ灏忓啓涓嶆晱鎰�
		arp.setContainerFactory(new CaseInsensitiveContainerFactory(true));
		_MappingKit.mapping(arp);
		me.add(arp);
		// arp.addMapping("user", User.class);
		//EhCachePlugin 
		System.out.println("璺緞:"+PathKit.getRootClassPath()+File.separator+"ehcache.xml");
		me.add(new EhCachePlugin());
	}
	
	/**
	 * 鎷︽埅鍣�,褰撴煇涓�Method 琚涓骇鍒殑鎷︽埅鍣ㄦ墍鎷︽埅锛屾嫤鎴櫒鍚勭骇鍒墽琛岀殑娆″簭渚濇涓猴細Global銆�
	 * Inject銆丆lass銆丮ethod锛屽鏋滃悓绾т腑鏈夊涓嫤鎴櫒锛岄偅涔堝悓绾т腑鐨勬墽琛屾搴忔槸锛氶厤缃湪鍓嶉潰鐨�鍏堟墽琛屻� 鎷︽埅鍣ㄤ粠涓婂埌涓嬩緷娆″垎涓�
	 * Global銆両nject銆丆lass銆丮ethod 鍥涗釜灞傛锛孋lear 鐢ㄤ簬娓呴櫎鑷韩 鎵�灞傛浠ヤ笂灞傜殑鎷︽埅鍣ㄣ�
	 */
	@Override
	public void configInterceptor(Interceptors me) {
		// 娣诲姞鎺у埗灞傚叏灞�嫤鎴櫒
		me.addGlobalActionInterceptor(new AuthInterceptor());
		// 娣诲姞涓氬姟灞傚叏灞�嫤鎴櫒
		// me.addGlobalServiceInterceptor(new TimeInterceptor());
		// 涓哄吋瀹硅�鐗堟湰淇濈暀鐨勬柟娉曪紝鍔熻兘涓巃ddGlobalActionInterceptor瀹屽叏涓�牱
		// me.add(new AuthInterceptor());
		/**
		 * 涓婁緥涓殑 TxByRegex 鎷︽埅鍣ㄥ彲閫氳繃浼犲叆姝ｅ垯琛ㄨ揪寮忓 action 杩涜鎷︽埅锛屽綋 actionKey 琚
		 * 鍒欏尮閰嶄笂灏嗗紑鍚簨鍔°�TxByActionKeys 鍙互瀵规寚瀹氱殑 actionKey 杩涜鎷︽埅骞跺紑鍚簨鍔★紝 TxByMethods
		 * 鍙互瀵规寚瀹氱殑 method 杩涜鎷︽埅骞跺紑鍚簨鍔°� 娉ㄦ剰锛歁ySql 鏁版嵁搴撹〃蹇呴』璁剧疆涓�InnoDB 寮曟搸鏃舵墠鏀寔浜嬪姟锛孧yISAM
		 * 骞朵笉鏀寔浜嬪姟
		 */
		// me.add(new TxByMethodRegex("(.*save.*|.*update.*)")); me.add(new
		// TxByMethods("save", "update"));
		// me.add(new TxByActionKeyRegex("/trans.*")); me.add(new
		// TxByActionKeys("/tx/save", "/tx/update"));

	}

	/**
	 * 锛孒andler 鍙互鎺ョ鎵�湁 web 璇锋眰锛屽苟瀵瑰簲鐢ㄦ嫢鏈夊畬鍏ㄧ殑鎺у埗鏉冿紝鍙互寰堟柟渚垮湴瀹炵幇鏇撮珮灞傜殑鍔熻兘鎬ф墿 灞�
	 */
	@Override
	public void configHandler(Handlers me) {
		// me.add(new ResourceHandler());
		// 娣诲姞druid鐩戞帶
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
