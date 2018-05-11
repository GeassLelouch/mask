package com.shell.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

/**
 *
 * 日期:2016年12月31日
 *
 * @author Shell
 *
 *         Copyright © 2016 Shell. All rights reserved
 */
@Entity
@Table(name = "product")
public @Data class Product implements java.io.Serializable {
	
	private static final long serialVersionUID = 8795837778673508258L;
 
	@Id
	@GeneratedValue
	@Column(name = "P_Id")
	private String id;

	@Column(name = "P_NAME")
	private String name;

	@Column(name = "SKINTYPE")
	private String skinType;

	@Column(name = "FEATURE")
	private String feature;

	@Column(name = "PRICE")
	private BigDecimal price;

	@Column(name = "STOCK")
	private BigDecimal stock;

	@Column(name = "SALES")
	private BigDecimal sales;
	
	@Transient
	private List<Product> dtoList = new ArrayList<Product>();
	
	@Transient
	private String salesPercentage;

}
