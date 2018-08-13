package com.extracodigo.pcs.controller.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.extracodigo.pcs.controller.DomApi;
import com.extracodigo.pcs.entity.Post;
import com.extracodigo.pcs.entity.Source;
import com.extracodigo.pcs.service.SourceService;

@Controller("domApiImpl")
public class DomApiImpl implements DomApi{
	
	private static final Logger logger = LoggerFactory.getLogger(DomApiImpl.class);
	
	@Autowired
	@Qualifier("sourceServiceImpl")
	private SourceService sourceService;

	@Override
	public List<Post> getPostsBySource(Source source) {
		List<Post> posts = new ArrayList<Post>();
		try {
			Document dom = Jsoup.connect(source.getUrl()).get();
			Elements container = dom.select(source.getSelectorContainer());
			posts = container.stream().map((el) -> getPostFromElement(el, source)).collect(Collectors.toList());	
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return posts;
	}
	
	private Post getPostFromElement(Element element, Source source) {
		Post post = new Post();
		post.setTitle(element.select(source.getSelectorTitle()).text());
		post.setDescription(element.select(source.getSelectorDescription()).text());
		post.setUrlToImage(element.select(source.getSelectorImg()).attr("href"));
		post.setUrl(element.select(source.getSelectorTitle()).attr("href"));
		post.setSource(source);
		
		return post;
	}

	public void setSourceService(SourceService sourceService) {
		this.sourceService = sourceService;
	}

}
