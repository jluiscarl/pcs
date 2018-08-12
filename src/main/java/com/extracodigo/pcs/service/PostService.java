package com.extracodigo.pcs.service;

import java.util.List;

import com.extracodigo.pcs.entity.Post;

public interface PostService {
	public Post create(Post post);
	public Post update(Post post);
	public Post getById(Long id);
	public List<Post> getAll();
	public void delete(Long id);
}
