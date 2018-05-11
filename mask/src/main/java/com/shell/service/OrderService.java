package com.shell.service;

import java.util.List;

import com.shell.model.Order;
import com.shell.model.Product;



/**
 *
 * 日期:2016年12月31日
 *
 * @author Shell
 *
 * Copyright © 2016 Shell. All rights reserved
 */
public interface OrderService {
	public List<Order> findAll();
	
	public Order findByPK(String pk);
	
	public Order save(Order obj);
}
