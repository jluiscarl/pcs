package com.extracodigo.pcs.external.api;

import java.util.List;

import com.extracodigo.pcs.entity.Post;
import com.extracodigo.pcs.entity.Source;

public interface DomApi {
	public List<Post> getPostsBySource(Source source);
	public String getContentByPost(Post post);
}
