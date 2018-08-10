package com.extracodigo.pcs.repository;

import java.util.List;

import com.extracodigo.pcs.entity.Post;

public interface PostRepository {
	public List<Post> getAll();
	public Post getById(Long id);
	public Post saveOrUpdate(Post post);
	public void delete(Post post);
}
