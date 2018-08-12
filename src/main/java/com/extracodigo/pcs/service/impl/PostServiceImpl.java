package com.extracodigo.pcs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.extracodigo.pcs.entity.Post;
import com.extracodigo.pcs.repository.PostRepository;
import com.extracodigo.pcs.service.PostService;

@Service("postServiceImpl")
public class PostServiceImpl implements PostService {
	
	@Autowired
	@Qualifier("postRepositoryHibernate")
	private PostRepository postRepository;

	@Override
	public Post create(Post post) {
		return postRepository.saveOrUpdate(post);
	}

	@Override
	public Post update(Post post) {
		return postRepository.saveOrUpdate(post);
	}

	@Override
	public Post getById(Long id) {
		return postRepository.getById(id);
	}

	@Override
	public List<Post> getAll() {
		return postRepository.getAll();
	}

	@Override
	public void delete(Long id) {
		postRepository.delete(postRepository.getById(id));
	}

	public void setPostRepository(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

}
