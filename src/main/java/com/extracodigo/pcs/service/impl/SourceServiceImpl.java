package com.extracodigo.pcs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.extracodigo.pcs.entity.Source;
import com.extracodigo.pcs.repository.SourceRepository;
import com.extracodigo.pcs.service.SourceService;

@Service("sourceServiceImpl")
public class SourceServiceImpl implements SourceService {
	
	@Autowired
	@Qualifier("sourceRepositoryHibernate")
	private SourceRepository sourceRepository;

	@Override
	public Source create(Source source) {
		return sourceRepository.saveOrUpdate(source);
	}

	@Override
	public Source update(Source source) {
		return sourceRepository.saveOrUpdate(source);
	}

	@Override
	public Source getById(Long id) {
		return sourceRepository.getById(id);
	}

	@Override
	public List<Source> getAll() {
		return sourceRepository.getAll();
	}

	@Override
	public void delete(Long id) {
		sourceRepository.delete(sourceRepository.getById(id));
	}
	
	public void setSourceRepository (SourceRepository sourceRepository) {
		this.sourceRepository = sourceRepository;
	}

}
