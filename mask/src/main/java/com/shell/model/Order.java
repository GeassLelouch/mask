package com.shell.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "product_order")
public @Data class Order{
	
	@Id
	@Column(name = "O_ID")
	private String oId;

	@Column(name = "M_ID")
	private String mId;

	@Temporal(TemporalType.DATE)
	@Column(name = "ORDER_DATE")
	private Date orderDate;

	@Column(name = "TOTAL")
	private BigDecimal total;

	@Column(name = "PAY_STATEMENT")
	private boolean payStatement;

	@Column(name = "MESSAGE")
	private String message;

}
