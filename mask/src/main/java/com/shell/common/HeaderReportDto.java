
package com.shell.common;

import lombok.Data;

/**
 * 
 * 日期:2017年8月6日
 * 
 * @author Shell
 *
 * Copyright © 2017 Shell. All rights reserved
 */
public @Data class HeaderReportDto {
	//程式代號
	private String programId;
	
	//列印者
	private String printer;
	
	//網站名
	private String webName;
	
	//報表名稱
	private String reportName;
}
