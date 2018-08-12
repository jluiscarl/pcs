package com.extracodigo.pcs.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.extracodigo.pcs.entity.Source;
import com.extracodigo.pcs.repository.SourceRepository;

public class SourceServiceImplTest {
	
	private static SourceServiceImpl sourceServiceImpl;
	
	private static SourceRepository sourceRepository;
	private static Source sourceResult;
	
	private static final String NAME = "EXTRACODIGO";
	private static final String URL = "http://extracodigo.blogspot.com";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sourceResult = new Source();
		sourceResult.setId(1L);
		sourceResult.setName(NAME);
		sourceResult.setUrl(URL);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		sourceRepository = Mockito.mock(SourceRepository.class);
		
		sourceServiceImpl = new SourceServiceImpl();
		sourceServiceImpl.setSourceRepository(sourceRepository);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createTest() {
		//GIVE
		Source source = new Source();
		source.setName(NAME);
		source.setUrl(URL);
		
		Mockito.when(sourceRepository.saveOrUpdate(source)).thenReturn(sourceResult);
		
		//WHEN
		Source result = sourceServiceImpl.create(source);
		
		//THEN		
		assertNotNull(result);
		assertNotNull(result.getId());
		assertTrue(result.getId() instanceof Long);
		assertTrue(result.getName().equals(source.getName()));
		assertTrue(result.getUrl().equals(source.getUrl()));
	}
	
	@Test(expected=Exception.class)
	public void createNullTest() {
		//GIVE
		Source source = null;
		
		Mockito.when(sourceRepository.saveOrUpdate(source)).thenThrow(HibernateException.class);
		
		//WHEN
		sourceServiceImpl.create(source);
		
		//THEN
	}
	
	@Test
	public void updateTest() {
		//GIVE
		Source source = new Source();
		source.setId(1L);
		
		Mockito.when(sourceRepository.saveOrUpdate(source)).thenReturn(sourceResult);
		
		//WHEN
		Source result = sourceServiceImpl.update(source);
		
		//THEN		
		assertNotNull(result);
		assertNotNull(result.getId());
		assertTrue(result.getId() instanceof Long);
		assertFalse(result.getName().equals(source.getName()));
		assertFalse(result.getUrl().equals(source.getUrl()));
	}
	
	@Test(expected=Exception.class)
	public void updateNullTest() {
		//GIVE
		Source source = null;
		
		Mockito.when(sourceRepository.saveOrUpdate(source)).thenThrow(HibernateException.class);
		
		//WHEN
		sourceServiceImpl.update(source);
		
		//THEN
	}
	
	@Test
	public void getByIdTest() {
		//GIVE
		Long id = 1L;
		
		Mockito.when(sourceRepository.getById(id)).thenReturn(sourceResult);
		
		//WHEN
		Source result = sourceServiceImpl.getById(id);
		
		//THEN		
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		assertNotNull(result.getUrl());
		assertTrue(result.getId() instanceof Long);
	}
	
	@Test
	public void getByAllTest() {
		//GIVE
		
		Mockito.when(sourceRepository.getAll()).thenReturn(new ArrayList<Source>());
		
		//WHEN
		List<Source> results = sourceServiceImpl.getAll();
		
		//THEN		
		assertNotNull(results);
	}
	
	@Test
	public void deleteTest() {
		//GIVE
		Source source = new Source();
		source.setId(1L);
		
		Mockito.when(sourceRepository.getById(source.getId())).thenReturn(source);
		Mockito.doNothing().when(sourceRepository).delete(source);
		
		//WHEN
		sourceServiceImpl.delete(1L);
		
		//THEN
		Mockito.verify(sourceRepository).getById(source.getId());
		Mockito.verify(sourceRepository).delete(source);
	}
	
	@Test(expected=Exception.class)
	public void deleteByIdNotExistTest() {
		//GIVE
		Long idNotExist = 1L;
		Source source = null;
		
		Mockito.when(sourceRepository.getById(idNotExist)).thenReturn(source);
		Mockito.doThrow(HibernateException.class).when(sourceRepository).delete(source);
		
		//WHEN
		sourceServiceImpl.delete(idNotExist);
		
		//THEN
		Mockito.verify(sourceRepository).getById(idNotExist);
		Mockito.verify(sourceRepository).delete(source);
	}

}
