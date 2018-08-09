package com.extracodigo.pcs.repository;

import java.util.List;

import com.extracodigo.pcs.entity.Source;

public interface SourceRepository {
	public List<Source> getAll();
	public Source getById(Long id);
	public Source saveOrUpdate(Source source);
	public void delete(Source source);
}
