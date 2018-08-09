package com.extracodigo.pcs.repository.impl;


import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.extracodigo.pcs.entity.Source;
import com.extracodigo.pcs.repository.Connection;
import com.extracodigo.pcs.repository.SourceRepository;

@Repository("sourceRepositoryHibernate")
public class SourceRepositoryImpl implements SourceRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(SourceRepositoryImpl.class);
	
	
	@Autowired
	@Qualifier("ConnectionImpl")
	private Connection connection;
	
	private Session session;

	
	@Override
	public List<Source> getAll() {
		logger.info("getAll()");
		return null;
	}

	@Override
	public Source getById() {
		logger.info("getById()");
		return null;
	}

	@Override
	public Source create(Source source) {
		session = (Session) connection.getSession();
		session.beginTransaction();
		session.save(source);
		session.getTransaction().commit();
		return source;
	}

	@Override
	public Source update(Source source) {
		logger.info("update()");
		return null;
	}

	@Override
	public void delete() {
		logger.info("delete()");
		
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	

}
