package com.shell.dao;

import java.util.List;

import com.shell.model.Order;


/**
 *
 * 日期:2016年12月31日
 *
 * @author Shell
 *
 * Copyright © 2016 Shell. All rights reserved
 */
public interface OrderDao {
	public List<Order> findAll();
	
	public Order findByPK(String pk);
	
	public Order save(Order model);
	
}

