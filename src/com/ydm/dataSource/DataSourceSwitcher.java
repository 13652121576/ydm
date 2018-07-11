package com.ydm.dataSource;

import java.util.Date;

import com.ydm.util.DateUtils;


public class DataSourceSwitcher {
	@SuppressWarnings("rawtypes")
	private static final ThreadLocal contextHolder = new ThreadLocal();
	@SuppressWarnings("unchecked")
	public static void setDataSource(String dataSource) {
		contextHolder.set(dataSource);
	}

	public static void setMaster() {
		try {
			clearDataSource();
			setDataSource("master");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	public static void setSlave() {
		try {
			clearDataSource();
			// 随机调用slave
			StringBuffer slaveString = new StringBuffer("");
			slaveString.append("slave");
			setDataSource(slaveString.toString());
			// 记录随机调用的日志
			StringBuffer logContent = new StringBuffer("");
			logContent.append(DateUtils.formatDatetime(new Date(),"yyyy-MM-dd hh:mm:ss"));
			logContent.append("   ");
			logContent.append(" invoke ");
			logContent.append(slaveString.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String getDataSource() {
		return (String) contextHolder.get();
	}

	public static void clearDataSource() {
		contextHolder.remove();
	}
}
