package com.extracodigo.pcs.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.extracodigo.pcs.controller.DomApi;
import com.extracodigo.pcs.entity.Post;
import com.extracodigo.pcs.entity.Source;
import com.extracodigo.pcs.service.SourceService;

@Controller("domApiImpl")
public class DomApiImpl implements DomApi{
	
	@Autowired
	@Qualifier("sourceServiceImpl")
	private SourceService sourceService;

	@Override
	public List<Post> getPostsBySource(Source source) {
		List<Source> sources = sourceService.getAll();
		return null;
	}

}
