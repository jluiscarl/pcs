package com.extracodigo.pcs.service;

import java.util.List;

import com.extracodigo.pcs.entity.Source;

public interface SourceService {
	public Source create(Source source);
	public Source update(Source source);
	public Source getById(Long id);
	public List<Source> getAll();
	public void delete(Long id);
}
