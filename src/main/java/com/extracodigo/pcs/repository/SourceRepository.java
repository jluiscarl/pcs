package com.extracodigo.pcs.repository;

import java.util.List;

import com.extracodigo.pcs.entity.Source;

public interface SourceRepository {
	public List<Source> getAll();
	public Source getById();
	public Source create(Source source);
	public Source update(Source source);
	public void delete();
}
