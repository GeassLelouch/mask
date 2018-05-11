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
@Table(name = "Member")
public @Data class Member{
	
	@Id
	@Column(name = "M_ID")
	private String mId;
	
	@Column(name = "USER_NAME")
	private String userName;

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDAY")
	private Date birthday;

	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "M_NAME")
	private String name;	
	
	@Column(name = "M_PASSWORD")
	private String mPassword;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "ADDRESS")
	private String address;	

}
