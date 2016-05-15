package com.giscafer.physicaltable.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.giscafer.physicaltable.Constant.ConfigConstant;
import com.giscafer.physicaltable.service.IReportcardService;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class ReportcardServiceImpl implements IReportcardService {

	public Map<String, Object> getReportList(int offset, int limit,
			String studentid) {
		int total = Db.queryLong(
				"select count(id) from " + ConfigConstant.REPORTCARDTABLE)
				.intValue();
		if (total == 0) {
			return null;
		} else {
			String whereString="";
			if(studentid!=null && !"".equals(studentid)){
				whereString=" where studentid like '%"+studentid+"%' ";
			}
			String sql="select * from "
					+ ConfigConstant.REPORTCARDTABLE+whereString
					+ " order by createtime desc limit ?,?";
			List<Record> records = Db.find(sql, offset, limit);
			Map<String, Object> datas = new HashMap<String, Object>(2);
			datas.put("rows", records);
			datas.put("total", total);
			return datas;
		}
	}

	@Override
	public int getNewData() {
		return Db
				.queryLong(
						"select count(id) from "
								+ ConfigConstant.REPORTCARDTABLE
								+ " where DATE_FORMAT(NOW(),'%Y-%m-%d')=DATE_FORMAT(createtime,'%Y-%m-%d')")
				.intValue();
	}
}
