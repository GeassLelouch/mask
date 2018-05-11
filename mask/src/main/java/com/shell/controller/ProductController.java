package com.shell.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shell.constant.Constant;
import com.shell.model.Member;
import com.shell.model.Product;
import com.shell.service.MaskService;

/**
 *
 * 日期:2017年1月3日
 *
 * @author Shell
 *
 * Copyright © 2016 Shell. All rights reserved
 */
@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private MaskService maskService;
	
	private static Member member;
	
	private String id;
	private Product product;
	
	private static String SEARCH = "product/search.jsp";
//	private static String SHOW = "product/show";
	private static String SHOW = "product/show.jsp";
	
	@RequestMapping(value = "/search",method = RequestMethod.GET)
	public ModelAndView search(HttpServletRequest req, HttpServletResponse res) {
		product = new Product();
		ModelAndView model = new ModelAndView(SEARCH);
		model.addObject("product", product);
		model.addObject("member", member);
		return model;	
	}

	@RequestMapping(value = "/show",method = RequestMethod.POST)
//	public String show(ModelMap model) {
	public ModelAndView show(HttpServletRequest req, HttpServletResponse res) {
		
		req.setAttribute(Constant.PARTIAL, SHOW);
//		req.setAttribute(Constant.TEMPLATE, Constant.TEMPLATE_PAGE);
		
		List<Product> productList = maskService.findAll();
		ModelAndView model = new ModelAndView(Constant.TEMPLATE_PAGE);
		model.addObject("productList", productList);
		model.addObject("member", member);
		return model;	
		
//		return new ModelAndView(Constant.TEMPLATE_PAGE);
		
		
		
//		String id = req.getParameter("id");
		
//		List<Product> productList = maskService.findAll();
//		ModelAndView model = new ModelAndView(SHOW);
//		model.addObject("productList", productList);
//		model.addObject("member", member);
//		return model;	
		
		
		
//		ModelAndView model = new ModelAndView(SHOW);
//		model.addObject("productList", productList);
//
//		return model;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
