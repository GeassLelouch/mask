package com.shell.repository;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

//import org.apache.commons.lang.StringUtils; 
import org.apache.log4j.Logger;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * 日期:2017年1月3日
 *
 * @author Shell
 *
 * Copyright © 2016 Shell. All rights reserved
 */
@SuppressWarnings("unchecked")
@NoRepositoryBean
public class GenericRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements GenericRepository<T, ID>, Serializable {
	  private static final long serialVersionUID = 1L;
	  static Logger logger = Logger.getLogger(GenericRepositoryImpl.class);

	  private final JpaEntityInformation<T, ?> entityInformation;
	  private final EntityManager em;
	  private final DefaultPersistenceProvider provider;
	  
	//  private final Class<T> entityClass;
	//  private final String entityName;

	  private Class<?> springDataRepositoryInterface;

	  public Class<?> getSpringDataRepositoryInterface() {
	    return springDataRepositoryInterface;
	  }

	  public void setSpringDataRepositoryInterface(Class<?> springDataRepositoryInterface) {
	    this.springDataRepositoryInterface = springDataRepositoryInterface;
	  }

	  public GenericRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
	      EntityManager entityManager, Class<?> springDataRepositoryInterface) {
	    super(entityInformation, entityManager);
	    this.entityInformation = entityInformation;
	    this.em = entityManager;
//	    this.entityClass=entityInformation.getJavaType();  
//	    this.entityName=entityInformation.getEntityName();
	    this.provider = DefaultPersistenceProvider.fromEntityManager(entityManager);
	    this.springDataRepositoryInterface = springDataRepositoryInterface;
	  }

	  public GenericRepositoryImpl(Class<T> domainClass, EntityManager em) {
	    this(JpaEntityInformationSupport.getMetadata(domainClass, em), em, null);
	  }

	  public <S extends T> S save(S entity) {
	    if (this.entityInformation.isNew(entity)) {
	      this.em.persist(entity);
	      flush();
	      return entity;
	    }
	    entity = this.em.merge(entity);
	    flush();
	    return entity;
	  }
	  
	  public T saveWithoutFlush(T entity){
	    return 
	     super.save(entity); 
	  }
	    
	  public List<T> saveWithoutFlush(Iterable<? extends T> entities) {
	    List<T> result = new ArrayList<T>();
	    if (entities == null) {
	      return result;
	    }
	    for (T entity : entities) {
	      result.add(saveWithoutFlush(entity));
	    }
	    return result;
	  }
}
