package com.extracodigo.pcs.services.interfaces;

import java.util.List;

public interface ServicesGeneric<T> {
	public List<T> getAll();
	public T getById();
	public T post();
	public T put();
	public T delete();
}
