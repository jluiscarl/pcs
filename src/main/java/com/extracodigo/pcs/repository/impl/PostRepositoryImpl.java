package com.extracodigo.pcs.repository.impl;

import java.util.List;

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
		return super.findById(Post.class, id);
	}

	@Override
	public Post saveOrUpdate(Post post) {
		return super.saveOrUpdate(post);
	}

	@Override
	public void delete(Post post) {
		super.delete(post);
	}

}