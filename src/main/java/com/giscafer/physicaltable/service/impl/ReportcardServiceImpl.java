package com.giscafer.physicaltable.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.giscafer.physicaltable.Constant.ConfigConstant;
import com.giscafer.physicaltable.service.IReportcardService;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class ReportcardServiceImpl implements IReportcardService {

	public Map<String, Object> getReportList(int offset, int limit) {
		int total = Db.queryLong("select count(id) from " + ConfigConstant.REPORTCARDTABLE).intValue();
		if (total == 0) {
			return null;
		} else {
			List<Record> records = Db.find("select * from "
					+ ConfigConstant.REPORTCARDTABLE
					+ " order by createtime desc limit ?,?", offset, limit);
			Map<String, Object> datas = new HashMap<String, Object>(2);
			datas.put("rows", records);
			datas.put("total", total);
			return datas;
		}
	}

	@Override
	public int getNewData() {
		return Db.queryLong("select count(id) from " + ConfigConstant.REPORTCARDTABLE+" where DATE_FORMAT(NOW(),'%Y-%m-%d')=FROM_UNIXTIME(createtime,'%Y-%m-%d')").intValue();
	}
}
