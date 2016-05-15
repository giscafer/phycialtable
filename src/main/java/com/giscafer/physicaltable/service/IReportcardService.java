package com.giscafer.physicaltable.service;

import java.util.Map;

public interface IReportcardService {
	public Map<String, Object> getReportList(int offset,int limit,String studentid);
	public int getNewData();
}
