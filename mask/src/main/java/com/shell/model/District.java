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
@Table(name = "district")
public @Data class District implements java.io.Serializable {
	
	private static final long serialVersionUID = 8795837778673508258L;
 
	@Id
	@GeneratedValue
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "city_id")
	private String cityId;

}
