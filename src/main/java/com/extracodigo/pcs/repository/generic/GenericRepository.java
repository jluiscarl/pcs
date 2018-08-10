package com.extracodigo.pcs.repository.generic;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.extracodigo.pcs.entity.AuditModel;
import com.extracodigo.pcs.repository.Connection;

@Repository("GenericRepository")
public abstract class GenericRepository {
	
	@Autowired
	@Qualifier("ConnectionImpl")
	private Connection connection;
	
	private Session session;
	private Transaction tx;
	
	private <T extends AuditModel> void initialize(T obj) {
		Hibernate.initialize(obj.getCreatedAt());
		Hibernate.initialize(obj.getUpdatedAt());
	}
	

    @SuppressWarnings("unchecked")
	protected <T extends AuditModel> T saveOrUpdate(T obj) {
    	T objResult = null;
        try {
            startOperation();
            session.saveOrUpdate(obj);
            objResult = (T) session.load(obj.getClass(), session.getIdentifier(obj));
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            session.close();
        }
        return objResult;
    }

    protected <T extends AuditModel> void delete(T obj) {
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

	protected <T extends AuditModel> T findById(Class<T> clazz, Long id) {
        T obj = null;
        try {
            startOperation();
            obj = session.load(clazz, id);
            initialize(obj);
            tx.commit();
        } catch (HibernateException e) {
        	if (e instanceof ObjectNotFoundException) {
        		obj = null;
        		tx.rollback();
        	} else {
        		handleException(e);
        	}
        } finally {
            session.close();
        }
        return obj;
    }

	@SuppressWarnings("unchecked")
	protected <T extends AuditModel> List<T> findAll(Class<T> clazz) {
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
