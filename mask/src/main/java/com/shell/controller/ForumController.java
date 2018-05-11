package com.shell.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shell.constant.Constant;
import com.shell.model.City;
import com.shell.model.Member;
import com.shell.model.Order;
import com.shell.model.OrderDetail;
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
@RequestMapping("/forum")
public class ForumController {
	
	@Autowired
	private MaskService dtoService;
	                              
	private static Member member;
	
	private static String PAGE = "forum/init.jsp";
	
	
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
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest req, HttpServletResponse res) {
		List<Product> productList = new ArrayList<Product>();
		String count = req.getParameter("count");
		String sumAmount = req.getParameter("sumAmount");
		Order obj = new Order();
		obj.setOId(UUID.randomUUID().toString().replace("-", ""));
		obj.setMId("123");
		obj.setOrderDate(new Date());
		obj.setPayStatement(false);
		obj.setTotal(new BigDecimal(sumAmount));
		obj.setMessage("");
		orderService.save(obj);
		for(int i = 0; i <= Integer.valueOf(count); i++) {
			String id = req.getParameter("idList[" + i + "]");/
			Product product = maskService.findByPK(id);
			productList.add(product);
			OrderDetail objDetail = new OrderDetail();
			objDetail.setPk(UUID.randomUUID().toString().replace("-", ""));
			objDetail.setOId(obj.getOId());
			objDetail.setPId(product.getId());
			objDetail.setOrderNo(i);
			objDetail.setPrice(product.getPrice());
			objDetail.setQuantity(1);
			orderDetailService.save(objDetail);
		}
		ModelAndView model = new ModelAndView(SHOW);
		model.addObject("member", member);
//		model.addObject("productList", productList);

		return model;
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
