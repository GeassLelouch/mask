package com.shell.repository;

import java.io.Serializable;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/**
 *
 * 日期:2017年1月3日
 *
 * @author Shell
 *
 * Copyright © 2016 Shell. All rights reserved
 */
public class DefaultRepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable>
		extends JpaRepositoryFactoryBean<T, S, ID> {

  protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
    return new DefaultRepositoryFactory(entityManager);
  }
}
