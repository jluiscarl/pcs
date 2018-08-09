package com.extracodigo.pcs.repository.impl;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.extracodigo.pcs.entity.Source;

public class SourceRepositoryImplTest {
	
	private static SourceRepositoryImpl sourceRepositoryImpl;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sourceRepositoryImpl = new SourceRepositoryImpl();
		sourceRepositoryImpl.setConnection(
				new ConnectionImpl(
						SourceRepositoryImplTest.class.getClassLoader().getResource("hibernate.cfg.xml").getPath()
				)
		);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void saveOrUpdateCreateTest() {
		//GIVE
		Source source = new Source();
		source.setName("Test");
		source.setUrl("url"); 
		
		//WHEN
		Source result = sourceRepositoryImpl.saveOrUpdate(source);
		
		//THEN
		assertNotNull(result);
		assertSame(result, source);
		assertNotNull(result.getId());
		assertTrue(result.getId() > 0);
		assertNotNull(source.getId());
		assertTrue(source.getId() > 0);
		assertTrue(result.getId().equals(source.getId()));
		assertTrue(result.getName().equals(source.getName()));
		assertTrue(result.getUrl().equals(source.getUrl()));
		assertNotNull(result.getCreatedAt());
		assertNotNull(result.getUpdatedAt());
		assertTrue(result.getCreatedAt().equals(source.getCreatedAt()));
		assertTrue(result.getUpdatedAt().equals(source.getUpdatedAt()));
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateCreateEmptyTest() {
		//GIVE
		Source source = new Source();
		
		//WHEN
		sourceRepositoryImpl.saveOrUpdate(source);		
		
		//THEN
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateCreateWithoutNameTest() {
		//GIVE
		Source source = new Source();
		source.setUrl("url");
		
		//WHEN
		sourceRepositoryImpl.saveOrUpdate(source);		
		
		//THEN
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateCreateWithoutUrlTest() {
		//GIVE
		Source source = new Source();
		source.setName("name");
		
		//WHEN
		sourceRepositoryImpl.saveOrUpdate(source);		
		
		//THEN
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateCreateNullTest() {
		//GIVE
		Source source = null;
		
		//WHEN
		sourceRepositoryImpl.saveOrUpdate(source);		
		
		//THEN
	}
	
	@Test
	public void saveOrUpdateUpdateTest() {
		//GIVE
		Source source = new Source();
		source.setName("Test");
		source.setUrl("url");
		sourceRepositoryImpl.saveOrUpdate(source);
		
		String nameUpdate = "Test update";
		Date updatedAt = source.getUpdatedAt();
		
		source.setName(nameUpdate);
		
		
		
		//WHEN
		Source result = sourceRepositoryImpl.saveOrUpdate(source);
		
		//THEN
		assertNotNull(result);
		assertSame(result, source);
		assertNotNull(result.getId());
		assertTrue(result.getId() > 0);
		assertNotNull(source.getId());
		assertTrue(source.getId() > 0);
		assertTrue(result.getId().equals(source.getId()));
		assertTrue(result.getName().equals(nameUpdate));
		assertTrue(result.getName().equals(source.getName()));
		assertTrue(result.getUrl().equals(source.getUrl()));
		assertNotNull(result.getCreatedAt());
		assertNotNull(result.getUpdatedAt());
		assertTrue(result.getCreatedAt().equals(source.getCreatedAt()));
		assertTrue(result.getUpdatedAt().equals(source.getUpdatedAt()));
		assertFalse(result.getUpdatedAt().toString().equals(updatedAt));
	}

	@Test	
	public void getByIdTest() {
		//GIVE
		Source source = new Source();
		source.setName("Test");
		source.setUrl("url");
		sourceRepositoryImpl.saveOrUpdate(source);
		
		//WHEN
		Source result = sourceRepositoryImpl.getById(source.getId());
		
		//THEN
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getCreatedAt());
		assertNotNull(result.getUpdatedAt());
		assertTrue(result.getId() > 0);
		assertNotNull(source.getId());
		assertTrue(source.getId() > 0);
		assertTrue(result.getId().equals(source.getId()));
		assertTrue(result.getName().equals(source.getName()));
		assertTrue(result.getUrl().equals(source.getUrl()));
		assertTrue(result.getCreatedAt().equals(source.getCreatedAt()));
		assertTrue(result.getUpdatedAt().equals(source.getUpdatedAt()));
	}

	@Test
	public void getByIdNotExistOrIdInvalidTest() {
		//GIVE
		
		//WHEN
		Source result = sourceRepositoryImpl.getById(Mockito.anyLong());
		
		//THEN
		assertNull(result);
	}
	
	@Test
	public void getAllTest() {
		//GIVE
		Source source = new Source();
		source.setName("Test");
		source.setUrl("url");
		sourceRepositoryImpl.saveOrUpdate(source);
		
		//WHEN
		List<Source> results = sourceRepositoryImpl.getAll();
		
		//THEN
		assertNotNull(results);
		assertTrue(results.isEmpty());
		assertTrue(results.size() == 1);
		Source result = results.get(0);
			assertNotNull(result);
			assertSame(result, source);
			assertNotNull(result.getId());
			assertNotNull(result.getCreatedAt());
			assertNotNull(result.getUpdatedAt());
			assertTrue(result.getId() > 0);
			assertNotNull(source.getId());
			assertTrue(source.getId() > 0);
			assertTrue(result.getId().equals(source.getId()));
			assertTrue(result.getName().equals(source.getName()));
			assertTrue(result.getUrl().equals(source.getUrl()));
			assertTrue(result.getCreatedAt().equals(source.getCreatedAt()));
			assertTrue(result.getUpdatedAt().equals(source.getUpdatedAt()));
		
	}
	
	@Test
	public void getAllEmptyTest() {
		//GIVE
		
		//WHEN
		List<Source> results = sourceRepositoryImpl.getAll();
		
		//THEN
		assertNotNull(results);
		assertTrue(results.isEmpty());
		
	}
}
