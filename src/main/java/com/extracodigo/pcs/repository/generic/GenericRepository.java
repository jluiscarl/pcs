package com.extracodigo.pcs.repository.generic;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.extracodigo.pcs.repository.Connection;

@Repository("GenericRepository")
public abstract class GenericRepository {
	
	@Autowired
	@Qualifier("ConnectionImpl")
	private Connection connection;
	
	private Session session;
	private Transaction tx;
	

    protected <T> T saveOrUpdate(T obj) {
        try {
            startOperation();
            session.saveOrUpdate(obj);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            session.close();
        }
        return obj;
    }

    protected <T> void delete(T obj) {
        try {
            startOperation();
            session.delete(obj);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
        	session.close();
        }
    }

    protected <T> T findById(Class<T> clazz, Long id) {
        T obj = null;
        try {
            startOperation();
            obj = session.load(clazz, id);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            session.close();
        }
        return obj;
    }

	@SuppressWarnings("unchecked")
	protected <T> List<T> findAll(Class<T> clazz) {
        List<T> objects = null;
        try {
            startOperation();
            objects = session.createQuery("from " + clazz.getName()).getResultList();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            session.close();
        }
        return objects;
    }

    protected void handleException(HibernateException e) throws HibernateException {
        tx.rollback();
        throw e;
    }

    protected void startOperation() throws HibernateException {
    	session = (Session) connection.getSession();
		tx = session.beginTransaction();
    }
    
    public void setConnection(Connection connection) {
    	this.connection = connection;
    }
}
