package com.extracodigo.pcs.dom;

import java.util.List;

import com.extracodigo.pcs.entity.Post;
import com.extracodigo.pcs.entity.Source;

public interface DomApi {
	public List<Post> buildPostBySource(Source source);
}
