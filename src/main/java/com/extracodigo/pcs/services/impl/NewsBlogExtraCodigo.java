package com.extracodigo.pcs.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extracodigo.pcs.services.interfaces.ServicesGeneric;

@RestController
@RequestMapping(path="/services")
public class NewsBlogExtraCodigo implements ServicesGeneric<NewsBlogExtraCodigo>{
	
	private static final Logger log = LoggerFactory.getLogger(NewsBlogExtraCodigo.class);
	
	@Override
	@RequestMapping("/news")
	public List<NewsBlogExtraCodigo> getAll() {
		log.info("ingresando a /services/news");
		return new ArrayList<NewsBlogExtraCodigo>();
	}

	@Override
	public NewsBlogExtraCodigo getById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NewsBlogExtraCodigo post() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NewsBlogExtraCodigo put() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NewsBlogExtraCodigo delete() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
