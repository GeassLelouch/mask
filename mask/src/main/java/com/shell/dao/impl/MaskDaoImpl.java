package com.shell.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.shell.HibernateUtil;
import com.shell.dao.MaskDao;
import com.shell.model.City;
import com.shell.model.District;
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
public class MaskDaoImpl implements MaskDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getPDFByCriteria(Map<String, Object> map) throws Exception {
		
		StringBuffer querySql = new StringBuffer();
		           
		//輸入要執行的SQL
		querySql.append(" select P_Id, ");
		querySql.append(" P_NAME, SKINTYPE, FEATURE, PRICE, STOCK, SALES ");
		querySql.append(" from product ");
        querySql.append(" where 1=1 ");
        
        //必須有傳值才成為查詢條件
        if(StringUtils.isNotBlank((String) map.get("priceStr")) && StringUtils.isNotBlank((String) map.get("priceEnd"))) {
        	querySql.append(" and price between " + (String) map.get("priceStr") + " and   ");
        	querySql.append(" " + (String) map.get("priceEnd") + " ");
        	
        }

        querySql.append(" order by P_Id ");
        
        //後面的class是接收資料的model
		Query query = entityManager.createNativeQuery(querySql.toString(), Product.class);
		
		//model容器
		List<Product> result = query.getResultList();								
		
        return result;		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<City> getAllCity() throws Exception {
		
		StringBuffer querySql = new StringBuffer();
		           
		//輸入要執行的SQL
		querySql.append(" select c.id, c.name ");
		querySql.append(" from city c ");
        
        querySql.append(" order by c.id ");
        
        //後面的class是接收資料的model
		Query query = entityManager.createNativeQuery(querySql.toString(), City.class);
		
		//model容器
		List<City> result = query.getResultList();								
		
        return result;		
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<District> getDistrictByCriteria(Map<String, Object> map) throws Exception {
		
		StringBuffer querySql = new StringBuffer();
		           
		//輸入要執行的SQL
		querySql.append(" select d.id, d.name, d.city_id ");
		querySql.append(" from district d ");
		querySql.append(" join city c  on d.city_id = c.id ");
        querySql.append(" where c.id = '"  + (String) map.get("cityId") + "'");
        
        querySql.append(" order by d.id ");
        
        //後面的class是接收資料的model
		Query query = entityManager.createNativeQuery(querySql.toString(), District.class);
		
		//model容器
		List<District> result = query.getResultList();								
		
        return result;		
	}	
	
	
	
	
	
	
	
	
	
	
	public List<Product> findAll() {
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
		List<Product> result = null;
		
		querySql.append(" SELECT * FROM product ");
		
		Query query = entityManager.createNativeQuery(querySql.toString(), Product.class);
		try {
			result = (List<Product>) query.getResultList();
		} catch(NoResultException ex) {
		}
		
		return result;
	}
	
	public Product findByPK(String pk) {
		
		StringBuffer querySql = new StringBuffer();
		List<Product> result = null;
		
		querySql.append(" SELECT * FROM product ");
		if(StringUtils.isNotBlank(pk)) {
			querySql.append(" WHERE P_Id = '" + pk + "' ");
		}
		
		Query query = entityManager.createNativeQuery(querySql.toString(), Product.class);
		try {
			result = (List<Product>) query.getResultList();
		} catch(NoResultException ex) {
		}
		
		if(result != null) {
			return result.get(0);
		}else {
			return null;
		}
	}	
	
	public Product save(Product model) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
		
		return model;
	}
	
	
	
	
	
}
