package com.extracodigo.pcs.repository.impl;

import java.util.List;

import org.hibernate.Hibernate;
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
		System.out.println(post);
		return super.saveOrUpdate(post);
	}

	@Override
	public void delete(Post post) {
		super.delete(post);
	}

}