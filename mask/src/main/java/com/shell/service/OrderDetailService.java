package com.shell.service;

import java.util.List;

import com.shell.model.Order;
import com.shell.model.OrderDetail;



/**
 *
 * 日期:2016年12月31日
 *
 * @author Shell
 *
 * Copyright © 2016 Shell. All rights reserved
 */
public interface OrderDetailService {
	public List<OrderDetail> findAll();
	
	public OrderDetail findByPK(String pk);
	
	public OrderDetail save(OrderDetail obj);
}
