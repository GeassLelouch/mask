package com.shell.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shell.dao.OrderDetailDao;
import com.shell.model.OrderDetail;
import com.shell.repository.OrderDetailRepository;
import com.shell.service.OrderDetailService;


/**
 *
 * 日期:2016年12月31日
 *
 * @author Shell
 *
 * Copyright © 2016 Shell. All rights reserved
 */
@Service("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService{
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private OrderDetailDao orderDetailDao;
	
	
	public List<OrderDetail> findAll() {
		return null;
	}
	
	public OrderDetail findByPK(String pk) {
		return null;
	}
	
	public OrderDetail save(OrderDetail obj) {
		return orderDetailRepository.save(obj);
	}


}
