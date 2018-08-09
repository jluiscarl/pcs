package com.extracodigo.pcs.repository.impl;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.extracodigo.pcs.entity.Source;
import com.extracodigo.pcs.repository.Connection;

@Repository("ConnectionImpl")
@Scope("session")
public class ConnectionImpl implements Connection{
	
	private final SessionFactory sessionFactory;

    {
    	Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Source.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public Object getSession() {
        return sessionFactory.openSession();
    }
}
