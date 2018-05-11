package com.shell.report;


import java.io.File;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.view.AbstractView;

import com.shell.common.DateUtil;
import com.shell.common.HeaderReportDto;
/**
 * 
 * 日期:2017年8月6日
 * 
 * @author Shell
 *
 * Copyright © 2017 Shell. All rights reserved
 */
public class JasperReportsView extends AbstractView {
	
	private static final Logger logger = Logger.getLogger(JasperReportsView.class);
	private String templateFileName = null;
	private String reportFileName=null;
	
	private String headerFileName = null;
	private String footerFileName = null;
	
	public static String JRXML_REPORT_PATH = "/report/";
	
	public JasperReportsView() {
		super();
	}

	public JasperReportsView(String templateFileName) {
		super();
		this.templateFileName = templateFileName;
	}
	/**
	 * 產生report樣式,
	 * @param templateFileName report樣版
	 * @param direction 橫式或直式
	 */
	public JasperReportsView(String templateFileName,Integer direction) {
		super();
		this.templateFileName = templateFileName;
		this.headerFileName = direction==1?"headerReport":(direction==2?"headerReportLandscape":"");
		this.footerFileName = direction==1?"footerReport":(direction==2?"footerReportLandscape":"");
	}
	                                                             
	/**
	 * 產生report樣式,
	 * @param templateFileName report樣版
	 * @param direction 橫式或直式
	 * @param reportFileName 產生的報表檔名
	 */
	public JasperReportsView(String templateFileName,Integer direction, String reportFileName) {
		super();
		this.templateFileName = templateFileName;
		this.reportFileName = reportFileName;
		this.headerFileName = direction==1?"headerReport":(direction==2?"headerReportLandscape":"");
		this.footerFileName = direction==1?"footerReport":(direction==2?"footerReportLandscape":"");
	}	

	/**
	 * 產生report樣式
	 * @param report樣版
	 * @param reportFileName 產生的報表檔名
	 */
	public JasperReportsView(String templateFileName, String reportFileName) {
		super();
		this.templateFileName = templateFileName;
		this.reportFileName = reportFileName;
	}

	public String getTemplateFileName() {
		return templateFileName;
	}

	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
	}

	public String getReportFileName() {
		return reportFileName;
	}

	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OutputStream out=null;
		try {
        	if(StringUtils.isBlank(templateFileName)){
        		throw new Exception("templateFileName is empty");
        	}
        	JasperReport jasperReport = null;
        	if(templateFileName.toLowerCase().indexOf(".jrxml")>0){
    			jasperReport =  JasperCompileManager.compileReport(new ClassPathResource(JRXML_REPORT_PATH+File.separator+templateFileName).getInputStream()) ;
    		}else{
    			jasperReport = (JasperReport) JRLoader.loadObject(new ClassPathResource(JRXML_REPORT_PATH+File.separator+templateFileName).getInputStream());
    		}
    		                                             
    		Map<String, Object> reportParams =new HashMap<String, Object>();
        	Object reportParamsObj = model.get("reportParams");
        	if(reportParamsObj!=null){
        		reportParams = (Map<String, Object>)reportParamsObj;
        	}
        	                                                
        	// header共用參數傳入header報表
        	if(headerFileName!=null){
        		HeaderReportDto dto = (HeaderReportDto) reportParams.get("HEADER_PARAMETER");
        		Map<String, Object> paramSub = new HashMap<String, Object>(); // 子報表PARAMETER
    	        paramSub.put("programId", dto.getProgramId()); // 子報表參數一
    	        paramSub.put("printer", dto.getPrinter()); // 子報表參數二
    	        paramSub.put("webName", dto.getWebName()); // 子報表參數二
    	        paramSub.put("reportName", dto.getReportName()); // 子報表參數二
    	        reportParams.put("HEADER_PARAMETER", paramSub); // 父報表放子報表的PARAMETER
    	        
        		//reportParams.put("HEADER_REPORT", (JasperReport) JRLoader.loadObject(new ClassPathResource(JRXML_REPORT_PATH+File.separator+subFileName+".jasper").getInputStream())); //塞到主報表的參數裡
        		reportParams.put("HEADER_REPORT", (JasperReport)JasperCompileManager.compileReport(new ClassPathResource(JRXML_REPORT_PATH+File.separator+headerFileName+".jrxml").getInputStream()));//塞到主報表的參數裡
        	}
        	
        	// header自訂參數傳入header報表
        	if(headerFileName!=null){
        		// Map 表示一筆資料，再用 List 裝起來，表示所有資料
		        Collection<Map<String, ?>> list = new ArrayList<Map<String, ?>>();
		        List<String> sList = (ArrayList<String>) reportParams.get("HEADER_FIELDS");
		        // 設定header報表自訂參數
		        for(String s:sList){
		        	Map<String, Object> data = new HashMap<String, Object>();
		            data.put("condition", s);
		            list.add(data);
		        }
		        // 使用 JRMapCollectionDataSource 打包資料成 JRDataSource 介面
		        // header報表的 data 塞到主報表的參數裡
		        reportParams.put("HEADER_FIELDS", new JRMapCollectionDataSource(list));
        	}

        	// 資料傳入footer報表
        	if(headerFileName!=null){
        		// Map 表示一筆資料，再用 List 裝起來，表示所有資料
		        Collection<Map<String, ?>> list = new ArrayList<Map<String, ?>>();
		        List<String> sList = (ArrayList<String>) reportParams.get("FOOTER_PARAMETER");
		        // 設定footer報表data
		        for(String s:sList){
		        	Map<String, Object> data = new HashMap<String, Object>();
		            data.put("fString", s);
		            list.add(data);
		        }
		        // 使用 JRMapCollectionDataSource 打包資料成 JRDataSource 介面
		        // 設定footer報表data
		        reportParams.put("FOOTER_PARAMETER", new JRMapCollectionDataSource(list));
		        // 設定footer報表版型		        
		        reportParams.put("FOOTER_REPORT", (JasperReport)JasperCompileManager.compileReport(new ClassPathResource(JRXML_REPORT_PATH+File.separator+footerFileName+".jrxml").getInputStream()));//塞到主報表的參數裡
        	}
        	 	
        	List<?> reportDataSource =  (List<?>)model.get("reportDataSource");
            JasperPrint jasperPrint = null;
            if(reportDataSource == null){
                jasperPrint = JasperFillManager.fillReport(jasperReport, reportParams);
            } else {
                jasperPrint = JasperFillManager.fillReport(jasperReport, reportParams,  new JRBeanCollectionDataSource(reportDataSource));
            }           
        	/*
        	if(reportDataSource == null) throw new RuntimeException("reportDataSource is null"); 
        	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportParams,  new JRBeanCollectionDataSource(reportDataSource));
        	 */
        	response.setContentType("application/x-pdf");
            response.setHeader("Content-disposition", "inline; filename="+URLEncoder.encode(genPdfFileName(),"UTF-8"));
            out = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
        	
		}catch (Exception e) {
			 //e.printStackTrace();
			logger.error("renderMergedOutputModel Exception",e);
            throw new RuntimeException();
        }finally{
            if(out!=null){
                try {
                	out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}

	/**
	 * 產生的報表PDF檔名主動加日期
	 */	
	private String genPdfFileName(){
		if(StringUtils.isBlank(reportFileName)){
			int pos=templateFileName.indexOf(File.separator);
			if(pos >=0){
				templateFileName = templateFileName.substring(pos+1);
			}
			return templateFileName.substring(0,templateFileName.lastIndexOf("."))+"_"+DateUtil.converToString(new Date(), "yyyyMMddHHmmss")+".pdf";
		}
		return reportFileName+"_"+DateUtil.converToString(new Date(), "yyyyMMddHHmmss")+".pdf";
    }
	/**
	 * 產生的報表PDF檔名不主動加日期
	 */	
    /*
	private String genPdfFileNameWithoutDate() throws UnsupportedEncodingException{
		if(StringUtils.isBlank(reportFileName)){
			int pos=templateFileName.indexOf(File.separator);
			if(pos >=0){
				templateFileName = templateFileName.substring(pos+1);
			}
			return templateFileName+"_"+DateUtil.converToString(new Date(), "yyyyMMddHHmmss")+".pdf";
		}
		return URLEncoder.encode(reportFileName,"UTF-8") +".pdf";
    }
	*/

}
