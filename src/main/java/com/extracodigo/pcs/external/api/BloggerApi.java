package com.extracodigo.pcs.controller;

import java.util.List;

import com.extracodigo.pcs.entity.Post;

public interface BloggerApi {
	public void post(Post post);
	public void post(List<Post> posts);
}
