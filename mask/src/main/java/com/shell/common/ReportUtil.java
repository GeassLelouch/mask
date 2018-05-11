package com.shell.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.shell.constant.Constant;
import com.shell.report.JasperReportsView;

/**
 * 
 * 日期:2017年8月6日
 * 
 * @author Shell
 *
 * Copyright © 2017 Shell. All rights reserved
 */
public class ReportUtil {
	
	public static String validateNull(Object obj) throws Exception {
		return StringUtils.isEmpty((String) obj) ? "" : (String) obj;
	}
	
	public static void writerCSV(List<List<Object>> list, String DESCRIPTION, String title, String REOPRT_NAME, 
			HttpServletResponse response) throws IOException {
		StringBuffer str = new StringBuffer();
		
    	String csvFileName = DESCRIPTION+"_"+ DateUtil.converToString(new Date(), "yyyyMMddHHmmss") + ".csv";
    	title = title + "\n\n";
    	
    	// 設定生成格式
		response.setContentType("application/octet-stream; charset=UTF-8");
		response.setHeader("Content-disposition", "attachment;filename=" + new String(csvFileName.getBytes("BIG5"), "ISO8859_1"));
		response.getOutputStream().write((title).getBytes("BIG5"));
		
		for(List<Object> rows : list) {
			for(Object row : rows) {
				str.append(row).append(",");
			}
			str.append("\n");
		}
		
		response.getOutputStream().write((str.toString()).getBytes("BIG5"));
		response.getOutputStream().write(("\n\n" + REOPRT_NAME).getBytes("BIG5"));
		response.getOutputStream().flush();
		response.getOutputStream().close();		
	}    	

	public static ModelAndView exportPdf(String FILE_NAME, String DESCRIPTION, String ID, String REOPRT_NAME, List<?> dtoList, 
			List<String> hString, int orientation) throws Exception {
		//主報表路徑名稱
    	String templateFile = File.separator+FILE_NAME;
         	
		Map<String, Object> reportParams = new HashMap<String, Object>();
		//header共用參數
		HeaderReportDto dto = new HeaderReportDto();
		//網站名稱
		dto.setWebName(Constant.WEB_NAME);
		//程式代號
		dto.setProgramId(ID);
		//報表的名稱
		dto.setReportName(REOPRT_NAME);
		//列印者
		dto.setPrinter(Constant.PRINTER);
		reportParams.put("HEADER_PARAMETER", dto);
        reportParams.put("HEADER_FIELDS", hString);
        // 設定footer報表data
        List<String> fString = new ArrayList<String>();
        //輸入報表的表尾
        fString.add(Constant.FOOT_STRING);
        reportParams.put("FOOTER_PARAMETER", fString);
		// 設定共用報表-結束  
               
		Map<String,Object> beans = new HashMap<String,Object>();
		beans.put("reportParams", reportParams);
		beans.put("reportDataSource", dtoList);
		
		return new ModelAndView(new JasperReportsView(templateFile, orientation, DESCRIPTION),beans);
	}
}
