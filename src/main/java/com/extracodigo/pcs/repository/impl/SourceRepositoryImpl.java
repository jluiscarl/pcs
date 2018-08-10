package com.extracodigo.pcs.repository.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.extracodigo.pcs.entity.Source;
import com.extracodigo.pcs.repository.SourceRepository;
import com.extracodigo.pcs.repository.generic.GenericRepository;

@Repository("sourceRepositoryHibernate")
public class SourceRepositoryImpl extends GenericRepository implements SourceRepository {
	
	@Override
	public List<Source> getAll() {
		return super.findAll(Source.class);
	}

	@Override
	public Source getById(Long id) {
		return super.findById(Source.class, id);
	}

	@Override
	public Source saveOrUpdate(Source source) {
		return super.saveOrUpdate(source);
	}

	@Override
	public void delete(Source source) {
		super.delete(source);
	}
	
}
