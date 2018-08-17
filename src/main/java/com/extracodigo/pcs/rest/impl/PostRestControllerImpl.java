package com.extracodigo.pcs.rest.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.extracodigo.pcs.controller.BloggerApi;
import com.extracodigo.pcs.controller.DomApi;
import com.extracodigo.pcs.entity.Post;
import com.extracodigo.pcs.entity.Source;
import com.extracodigo.pcs.rest.PostRestController;
import com.extracodigo.pcs.service.PostService;
import com.extracodigo.pcs.service.SourceService;

@RestController
@RequestMapping(path="/post")
public class PostRestControllerImpl implements PostRestController {
	
	private final static Logger logger = LoggerFactory.getLogger(PostRestControllerImpl.class);
	
	@Autowired
	@Qualifier("sourceServiceImpl")
	private SourceService sourceService;
	
	@Autowired
	@Qualifier("domApiImpl")
	private DomApi domApi;
	
	@Autowired
	@Qualifier("bloggerApiImpl")
	private BloggerApi bloggerApi;
	
	@Autowired
	@Qualifier("postServiceImpl")
	private PostService postService;

	
	@Override
	@RequestMapping(path="/build", method=RequestMethod.POST)
	public void build() {
		List<Source> sources = sourceService.getAll();
		List<List<Post>> posts = sources.stream()
				.map((source) -> domApi.getPostsBySource(source))
				.collect(Collectors.toList());
		posts.stream().forEach((postList) -> {
			postList.stream().forEach((post) -> {
				post.setPublished(false);
				postService.create(post);
			});
		});
	}

	@Override
	@RequestMapping(path="/publish", method=RequestMethod.POST)
	public void publish() {
		bloggerApi.post(postService.getByPublished(false));
	}
	
	@RequestMapping(path="/all", method=RequestMethod.GET)
	public List<Post> all() {
		return postService.getAll();
	}

}
