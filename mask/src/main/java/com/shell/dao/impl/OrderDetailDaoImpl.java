package com.shell.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.shell.HibernateUtil;
import com.shell.dao.OrderDetailDao;
import com.shell.model.OrderDetail;
import com.shell.model.Product;


/**
 *
 * 日期:2017年1月1日
 *
 * @author Shell
 *
 * Copyright © 2016 Shell. All rights reserved
 */
@Repository
public class OrderDetailDaoImpl implements OrderDetailDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<OrderDetail> findAll() {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//
//		session.beginTransaction();
//		
//		List<Object[]> result = null;
//		Product product = new Product();
//		
//		StringBuffer query = new StringBuffer();
////		query.append(" SELECT P_Id, P_NAME, SKINTYPE, FEATURE, PRICE, STOCK, SALES FROM product ");
//		query.append(" SELECT * FROM product ");
//		if(!StringUtils.isNotBlank(pk)) {
//			query.append(" WHERE P_Id = '" + pk + "' ");
//		}
//		 
//		Query q = session.createSQLQuery(query.toString());
//		result = q.list();
//		for(Object[] i : result) {
//			product.setId((String) i[0]);
//			product.setName((String) i[1]);
//			product.setSkinType((String) i[2]);
//			product.setFeature((String) i[3]);
//			product.setPrice(i[4] == null?null:new BigDecimal(i[4].toString()));
//			product.setSales(i[4] == null?null:new BigDecimal((String)i[5].toString()));
//			product.setStock(i[4] == null?null:new BigDecimal((String)i[6].toString()));
//		}
		
//		session.getTransaction().commit();
//		return product;
		
		StringBuffer querySql = new StringBuffer();
		List<OrderDetail> result = null;
		
		querySql.append(" SELECT * FROM product ");
		
		Query query = entityManager.createNativeQuery(querySql.toString(), Product.class);
		try {
			result = (List<OrderDetail>) query.getResultList();
		} catch(NoResultException ex) {
		}
		
		return result;
	}
	
	public OrderDetail findByPK(String pk) {
		
		StringBuffer querySql = new StringBuffer();
		List<OrderDetail> result = null;
		
		querySql.append(" SELECT * FROM product ");
		if(StringUtils.isNotBlank(pk)) {
			querySql.append(" WHERE P_Id = '" + pk + "' ");
		}
		
		Query query = entityManager.createNativeQuery(querySql.toString(), Product.class);
		try {
			result = (List<OrderDetail>) query.getResultList();
		} catch(NoResultException ex) {
		}
		
		if(result != null) {
			return result.get(0);
		}else {
			return null;
		}
	}	
	
	public OrderDetail save(OrderDetail model) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
		
		return model;
	}
	
	
	
	
	
}
