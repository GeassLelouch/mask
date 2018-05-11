package com.shell.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shell.dao.MemberDao;
import com.shell.model.Member;
import com.shell.repository.MemberRepository;
import com.shell.service.MemberService;


/**
 *
 * 日期:2016年12月31日
 *
 * @author Shell
 *
 * Copyright © 2016 Shell. All rights reserved
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MemberDao memberDao;
	
	public List<Member> findAll() {
		return null;
	}
	
	public Member findByCriteria(String userName) {
		return memberDao.findByCriteria(userName);
	}
	
	public Member save(Member obj) {
		return memberRepository.save(obj);
	}


}
