package com.extracodigo.pcs.rest.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.extracodigo.pcs.entity.Source;
import com.extracodigo.pcs.rest.SourceRestController;
import com.extracodigo.pcs.service.SourceService;

@RestController
@RequestMapping(path="/source")
public class SourceRestControllerImpl implements SourceRestController {
	
	@Autowired
	@Qualifier("sourceServiceImpl")
	private SourceService sourceService;
	
	@Override
	@RequestMapping(path="/create", method=RequestMethod.POST)
	public Source create(@RequestBody(required=true) Source source) {
		return sourceService.create(source);
	}

	@Override
	@RequestMapping(path="/update", method=RequestMethod.PUT)
	public Source update(@RequestBody(required=true) Source source) {
		return sourceService.update(source);
	}
	
	@Override
	@RequestMapping(path="/delete/{id}", method=RequestMethod.DELETE)
	public void delete(@RequestParam(required=true) Long id) {
		sourceService.delete(id);
	}

	@Override
	@RequestMapping(path="/delete/{id}", method=RequestMethod.GET)
	public Source getById(@RequestParam(required=true) Long id) {
		return sourceService.getById(id);
	}

	@Override
	@RequestMapping(path="/all", method=RequestMethod.GET)
	public List<Source> gelAll() {
		return sourceService.getAll();
	}

}
