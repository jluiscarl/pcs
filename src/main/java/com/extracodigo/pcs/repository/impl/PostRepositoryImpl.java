package com.extracodigo.pcs.repository.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.extracodigo.pcs.entity.Post;
import com.extracodigo.pcs.repository.PostRepository;
import com.extracodigo.pcs.repository.generic.GenericRepository;

@Repository("postRepositoryHibernate")
public class PostRepositoryImpl extends GenericRepository implements PostRepository {

	@Override
	public List<Post> getAll() {
		return super.findAll(Post.class);
	}

	@Override
	public Post getById(Long id) {
		Post result = super.findById(Post.class, id);
		if (result != null) Hibernate.initialize(result.getSource());
		return result;
	}

	@Override
	public Post saveOrUpdate(Post post) {
		return super.saveOrUpdate(post);
	}

	@Override
	public void delete(Post post) {
		super.delete(post);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getByPublished(Boolean published) {
		List<Post> posts = null;
        try {
            super.startOperation();
            posts = super.session.createQuery("from " + Post.class.getName() + " where published = :published")
            									.setParameter("published", published)
            									.getResultList();
            tx.commit();
        } catch (HibernateException e) {
            super.handleException(e);
        } finally {
            session.close();
        }
        return posts;
	}

}