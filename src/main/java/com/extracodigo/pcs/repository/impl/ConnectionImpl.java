package com.extracodigo.pcs.repository.impl;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;

import com.extracodigo.pcs.entity.Post;
import com.extracodigo.pcs.entity.Source;
import com.extracodigo.pcs.repository.Connection;

@Repository("ConnectionImpl")
public class ConnectionImpl implements Connection{
	
	private String configurationFile = "";
	private final SessionFactory sessionFactory;

    {
    	Configuration configuration = new Configuration();
    	configuration = (configurationFile.equals("")) ? configuration.configure() : configuration.configure(configurationFile); 
        configuration.addAnnotatedClass(Source.class);
        configuration.addAnnotatedClass(Post.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
    
    public ConnectionImpl () {
    }
    
    public ConnectionImpl (String configurationFile) {
    	this.configurationFile = configurationFile;
    }

    public Object getSession() {
        return sessionFactory.openSession();
    }
}
