package com.shell.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shell.constant.Constant;
import com.shell.model.Member;
import com.shell.service.MaskService;
import com.shell.service.MemberService;

/**
 *
 * 日期:2017年1月3日
 *
 * @author Shell
 *
 * Copyright © 2016 Shell. All rights reserved
 */
@Controller
@RequestMapping("/index")
public class IndexController {
	
	@Autowired
	private MaskService maskService;
	
	@Autowired
	private MemberService memberService;	
	
	private static String INIT = "index.jsp";
	
	private static String REGISTER = "register.jsp";
	
	private static String LOGIN = "login.jsp";
	
	private static Member member;
	
//	@RequestMapping(value = "/init",method = RequestMethod.GET)
//	public ModelAndView search(HttpServletRequest req, HttpServletResponse res) {
	@RequestMapping(value = "/{rrid:.+}", method = RequestMethod.GET)
    public ModelAndView init(HttpServletRequest request,@PathVariable String rrid) {
		
			request.setAttribute(Constant.PARTIAL, INIT);
			request.setAttribute(Constant.TEMPLATE, Constant.TEMPLATE_PAGE);
			
			return new ModelAndView(Constant.TEMPLATE_PAGE);
			
//			String result = (String) req.getSession().getAttribute("result");
//			if(result == null) {
//				req.getSession().setAttribute("result", "false");
//				ModelAndView model = new ModelAndView(LOGIN);
//				model.addObject("member", member);
//				return model;	
//			}else {
//				req.getSession().setAttribute("result", "success");
//				request.setAttribute(Constant.PARTIAL, INIT);
//				request.setAttribute(Constant.PAGE, "123");
//				request.setAttribute(Constant.PARTIAL, INIT);
//				request.setAttribute(Constant.TEMPLATE, Constant.TEMPLATE_PAGE);
//				ModelAndView model = new ModelAndView();
//				model.addObject("member", member);
//				return model;	
//		}
	}
	
//    /**
//     * 初始頁面,查詢頁面
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/init", method = RequestMethod.POST)
//    public @ResponseBody ModelAndView init(HttpServletRequest request) {
//        
//    	return new ModelAndView();
//    }
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView model = new ModelAndView(LOGIN);
		model.addObject("member", member);
		return model;		
	}
	
	@RequestMapping(value = "/loginConfirm",method = RequestMethod.POST)
	public ModelAndView loginConfirm(HttpServletRequest req, HttpServletResponse res) {
		String userName = req.getParameter("userName");
		Member member = memberService.findByCriteria(userName);
		if(member == null) {
			req.getSession().setAttribute("result", "false");
			ModelAndView model = new ModelAndView(LOGIN);
			model.addObject("member", member);
			return model;	
		}else {
			req.getSession().setAttribute("result", "success");
			req.getSession().setAttribute("name", member.getName());
			ModelAndView model = new ModelAndView(INIT);
			model.addObject("member", member);
			return model;	
		}
	}
	
	@RequestMapping(value = "/out",method = RequestMethod.POST)
	public ModelAndView out(HttpServletRequest req, HttpServletResponse res) {
		Member member = new Member();
		req.getSession().removeAttribute("result");
		req.getSession().removeAttribute("name");
		ModelAndView model = new ModelAndView(INIT);
		model.addObject("member", member);
		return model;	
	}
	
	@RequestMapping(value = "/register",method = RequestMethod.GET)
	public ModelAndView register(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView model = new ModelAndView(REGISTER);
		model.addObject("member", member);
		return model;		
	}
	
	@RequestMapping(value = "/registerConfirm",method = RequestMethod.POST)
	public ModelAndView registerConfirm(HttpServletRequest req, HttpServletResponse res) {
		String userName = req.getParameter("userName");
		String mName = req.getParameter("mName");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String gender = req.getParameter("gender");
		String birthday = req.getParameter("birthday");
		String address = req.getParameter("address");
		String mPassword = req.getParameter("mPassword");
		
		Member member = new Member();
		member.setMId(UUID.randomUUID().toString().replace("-", ""));
		member.setName(mName);
		member.setMPassword(mPassword);
		member.setAddress(address);
		member.setEmail(email);
		member.setGender(gender);
		member.setUserName(userName);
		member.setPhone(phone);
		
		if(birthday != null) {
	        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	        format.setLenient(false);
	        Date d;
			try {
				d = format.parse(birthday);
				member.setBirthday(d);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		member = memberService.save(member);
		
		ModelAndView model = new ModelAndView(LOGIN);
		model.addObject("member", member);

		return model;		
	}
}
