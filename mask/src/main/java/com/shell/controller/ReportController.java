package com.shell.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shell.common.JsonResponse;
import com.shell.common.ReportUtil;
import com.shell.constant.Constant;
import com.shell.model.City;
import com.shell.model.District;
import com.shell.model.Member;
import com.shell.model.Product;
import com.shell.service.MaskService;

/**
 *
 * 日期:2017年1月3日
 *
 * @author Shell
 *
 * Copyright © 2017 Shell. All rights reserved
 */
@Controller
//action名稱
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private MaskService dtoService;
	                              
	private static Member member;
	
	private static String PAGE = "report/print.jsp";
	private static String DESCRIPTION = "第一張列印報表";
	private static String ID = "first";
	private static String FILE_NAME = "first.jrxml";
	private static String REOPRT_NAME = "商品銷售報表";
	
	private static String DESC_OF_TABLE = "第一張列印報表表格元素版";
	private static String ID_OF_TABLE = "firstOfTableElement";
	private static String FILE_NAME_OF_TABLE = "firstOfTableElement.jrxml";
	
	//方法名稱
	@RequestMapping(value = "/init",method = RequestMethod.POST)
	public ModelAndView init(HttpServletRequest req, HttpServletResponse res) {
		
		List<Product> productList = dtoService.findAll();
		
		List<City> cityList = null;
		try {
			cityList = dtoService.getAllCity();
		} catch (Exception e) {
		}
		
		req.getSession().setAttribute("city", cityList);
		
		req.setAttribute(Constant.PARTIAL, PAGE);
		req.setAttribute(Constant.TEMPLATE, Constant.TEMPLATE_PAGE);
		
		ModelAndView model = new ModelAndView(Constant.TEMPLATE_PAGE);
		//要傳回去的model
		model.addObject("productList", productList);
		model.addObject("member", member);
		return model;	
	}	
	
	@RequestMapping(value = "/exportPdf", method = RequestMethod.POST)
    public ModelAndView exportPdf(HttpServletRequest request) throws Exception {
		try{ 
			Map<String, Object> map = initMap(request);
			               
			List<Product> dtoList = initReturnList(map);
			           
			for(Product dto : dtoList) {
				String salesPercentage = "0%";
				BigDecimal sales = dto.getSales();
				BigDecimal stock = dto.getStock();
				//兩者乘除皆不得為零
		        if(dto.getSales().compareTo(BigDecimal.ZERO) > 0 && dto.getStock().compareTo(BigDecimal.ZERO) > 0) {
		        	//計算 銷售百分比 ，最後加上 %
		        	salesPercentage = sales.divide(stock, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP).toString().concat("%");
		        }
				dto.setSalesPercentage(salesPercentage);
			}
			      
			//header自訂參數(查詢條件string)
	        List<String> hString = new ArrayList<String>();
	        if(!StringUtils.isEmpty((String) map.get("priceStr"))) {
	        	hString.add("價格：" + (String) map.get("priceStr") + "~" + (String) map.get("priceEnd"));
	        }
  
			//最後一個參數：1:直向報表2:橫向報表
			return ReportUtil.exportPdf(FILE_NAME, DESCRIPTION, ID, REOPRT_NAME, dtoList, hString, 1);
		}catch(Exception e){
			throw e;
		}
	}
	
	@RequestMapping(value = "/exportPdfOfTableElement", method = RequestMethod.POST)
    public ModelAndView exportPdfOfTableElement(HttpServletRequest request) throws Exception {
		try{ 
			
			Map<String, Object> map = initMap(request);
			
			List<Product> dtoList = initReturnList(map);
			      
			List<Product> resultList = new ArrayList<Product>();
			Product result = new Product();
			result.setDtoList(dtoList);
	    	resultList.add(result);
	    	
			//header自訂參數(查詢條件string)
	        List<String> hString = new ArrayList<String>();
	        if(!StringUtils.isEmpty((String) map.get("priceStr"))) {
	        	hString.add("價格：" + (String) map.get("priceStr") + "~" + (String) map.get("priceEnd"));
	        }
  
			//1:直向報表2:橫向報表
			return ReportUtil.exportPdf(FILE_NAME_OF_TABLE, DESC_OF_TABLE, ID_OF_TABLE, REOPRT_NAME, resultList, hString, 1);
      
		}catch(Exception e){
			throw e;
		}
	}
	
	/**
     * 匯出CSV
     * @param request
     * @return
     */
	@RequestMapping(value = "/exportCsv", method = RequestMethod.POST)
	public @ResponseBody ModelAndView exportCsv(HttpServletRequest req, HttpServletResponse response) {

    	try {
			Map<String, Object> map = initMap(req);
            
			List<Product> dtoList = initReturnList(map);
			
			String title = DESCRIPTION;
	        if(!StringUtils.isEmpty((String) map.get("priceStr"))) {
	        	title = title +"\n" + "價格：" + map.get("priceStr") + "~" + map.get("priceEnd");
	        }
			 
			List<List<Object>> list = new ArrayList<List<Object>>();
			
			List<Object> rows = new ArrayList<Object>();
			rows.add("產品名稱");
			rows.add("適用膚質");
			rows.add("效果");
			rows.add("價格");
			rows.add("庫存");
			rows.add("銷售量");
			rows.add("銷售比例");
			
			list.add(rows);
			
			BigDecimal salesSum = BigDecimal.ZERO;
			
			for(Product dto : dtoList) {
				String salesPercentage = "0%";
				BigDecimal sales = dto.getSales();
				BigDecimal stock = dto.getStock();
				//兩者乘除皆不得為零
		        if(dto.getSales().compareTo(BigDecimal.ZERO) > 0 && dto.getStock().compareTo(BigDecimal.ZERO) > 0) {
		        	//計算 銷售百分比 ，最後加上 %
		        	salesPercentage = sales.divide(stock, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP).toString().concat("%");
		        }
				dto.setSalesPercentage(salesPercentage);
				
    			rows = new ArrayList<Object>();
	             
    			rows.add(dto.getName());
    			rows.add(dto.getSkinType());
    			rows.add(dto.getFeature());
    			rows.add(dto.getPrice());
    			rows.add(dto.getStock());
    			rows.add(dto.getSales());
    			rows.add(dto.getSalesPercentage());
    			               
    			list.add(rows);
    			
    			salesSum = salesSum.add(dto.getSales());
			}
			
			rows = new ArrayList<Object>();
			list.add(rows);
			     
			rows = new ArrayList<Object>();
			rows.add("合計");
			rows.add("");
			rows.add("");
			rows.add("");
			rows.add("");
			rows.add(salesSum);
			               
			list.add(rows);
    		
			ReportUtil.writerCSV(list, DESCRIPTION, title, REOPRT_NAME,  response);
			
    	} catch (Exception e) {
		}
		
    	return new ModelAndView(Constant.TEMPLATE_PAGE);
	}	
	
	@RequestMapping(value = "/getDistrict", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getDistrict(HttpServletRequest request, @RequestParam String cityId) {
		Map<String, Object> response = new HashMap<>();
		   
		try {
			List<District> district = new ArrayList<>();
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cityId", cityId);
			
			if(!StringUtils.isEmpty(cityId)) {
				district = dtoService.getDistrictByCriteria(map);
			}
			
			response.put("district", district);
			request.getSession().setAttribute("district", district);
		} catch (Exception e) {
		}
		
		JsonResponse jsonResponse = new JsonResponse(Constant.STATUS_CODE_OK, Constant.STATUS_MSG_OK);
		jsonResponse.setResponse(response);
		return jsonResponse.returnJsonResponse();
	}
	                                              
    public Map<String, Object> initMap(HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("priceStr", request.getParameter("priceStr"));
		map.put("priceEnd", request.getParameter("priceEnd"));

		return map;
	}
    
    public List<Product> initReturnList(Map<String, Object> map) throws Exception {
		
		List<Product> drrsList = dtoService.getPDFByCriteria(map);
		
		return drrsList;
	}
    
}
