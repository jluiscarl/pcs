package com.extracodigo.pcs.rest;

import java.util.List;

import com.extracodigo.pcs.entity.Source;

public interface SourceRestController {
	public Source create(Source source);
	public Source update(Source source);
	public void delete(Long id);
	public Source getById(Long id);
	public List<Source> gelAll();
}
