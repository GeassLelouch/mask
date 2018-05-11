package com.shell.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shell.dao.OrderDao;
import com.shell.model.Order;
import com.shell.repository.OrderRepository;
import com.shell.service.OrderService;


/**
 *
 * 日期:2016年12月31日
 *
 * @author Shell
 *
 * Copyright © 2016 Shell. All rights reserved
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> findAll() {
		return orderDao.findAll();
	}
	
	public Order findByPK(String pk) {
		return orderDao.findByPK(pk);
	}
	
	public Order save(Order obj) {
//		return orderDao.save(obj);
		return orderRepository.save(obj);
	}


}
