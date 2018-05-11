package com.shell.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shell.dao.MaskDao;
import com.shell.model.City;
import com.shell.model.District;
import com.shell.model.Product;
import com.shell.service.MaskService;


/**
 *
 * 日期:2016年12月31日
 *
 * @author Shell
 *
 * Copyright © 2016 Shell. All rights reserved
 */
@Service("maskService")
public class MaskServiceImpl implements MaskService{
	
	@Autowired
	private MaskDao maskDao;
	
	public List<Product> findAll() {
		return maskDao.findAll();
	}
	
	public Product findByPK(String pk) {
		return maskDao.findByPK(pk);
	}
	
	public List<Product> getPDFByCriteria(Map<String, Object> map) throws Exception {
		return maskDao.getPDFByCriteria(map);
	}
	
	public List<City> getAllCity() throws Exception {
		return maskDao.getAllCity();
	}
	
	public List<District> getDistrictByCriteria(Map<String, Object> map) throws Exception {
		return maskDao.getDistrictByCriteria(map);
	}


}
