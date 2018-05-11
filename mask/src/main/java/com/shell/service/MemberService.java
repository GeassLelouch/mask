package com.shell.service;

import java.util.List;

import com.shell.model.Member;



/**
 *
 * 日期:2016年12月31日
 *
 * @author Shell
 *
 * Copyright © 2016 Shell. All rights reserved
 */
public interface MemberService {
	public List<Member> findAll();
	
	public Member findByCriteria(String userName);
	
	public Member save(Member obj);
}
