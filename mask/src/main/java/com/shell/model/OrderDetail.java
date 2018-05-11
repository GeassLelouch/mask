package com.shell.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 *
 * 日期:2017年1月8日
 *
 * @author Shell
 *
 * Copyright © 2016 Shell. All rights reserved
 */
@Entity
@Table(name = "order_detail")
public @Data class OrderDetail{
	
	@Id
	@Column(name = "PK")
	private String pk;
	
	@Column(name = "ORDER_NO")
	private Integer orderNo;

	@Column(name = "O_ID")
	private String oId;

	@Column(name = "P_Id")
	private String pId;

	@Column(name = "QUANTITY")
	private int quantity;

	@Column(name = "PRICE")
	private BigDecimal price;

}
