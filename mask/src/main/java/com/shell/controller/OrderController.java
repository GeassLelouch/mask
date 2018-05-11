package com.shell.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shell.constant.Constant;
import com.shell.model.Member;
import com.shell.model.Order;
import com.shell.model.OrderDetail;
import com.shell.model.Product;
import com.shell.service.MaskService;
import com.shell.service.OrderDetailService;
import com.shell.service.OrderService;

/**
 *
 * 日期:2017年1月8日
 *
 * @author Shell
 *
 * Copyright © 2016 Shell. All rights reserved
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private MaskService maskService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	private static Member member;
	
	private static String INIT = "order/init.jsp";
	private static String SHOW = "order/show.jsp";
	
	@RequestMapping(value = "/init",method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest req, HttpServletResponse res) {
		BigDecimal sumAmount = new BigDecimal(0);
		List<Product> productList = new ArrayList<Product>();
		String count = req.getParameter("count");
		for(int i = 0; i <= Integer.valueOf(count); i++) {
			String id = req.getParameter("idList[" + i + "]");
			Product product = maskService.findByPK(id);
			sumAmount = sumAmount.add(product.getPrice());
			productList.add(product);
		}
		
		req.setAttribute(Constant.PARTIAL, INIT);
		req.setAttribute(Constant.TEMPLATE, Constant.TEMPLATE_PAGE);
		
		ModelAndView model = new ModelAndView(Constant.TEMPLATE_PAGE);
		model.addObject("productList", productList);
		model.addObject("sumAmount", sumAmount);
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
			String id = req.getParameter("idList[" + i + "]");
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

}
