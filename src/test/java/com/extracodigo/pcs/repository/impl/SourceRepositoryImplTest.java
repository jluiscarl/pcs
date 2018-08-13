package com.extracodigo.pcs.repository.impl;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		sourceRepositoryImpl = new SourceRepositoryImpl();
		sourceRepositoryImpl.setConnection(
				new ConnectionImpl(
						SourceRepositoryImplTest.class.getClassLoader().getResource("hibernate.cfg.xml").getPath()
				)
		);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void saveOrUpdateCreateTest() {
		//GIVE
		Source source = new Source();
		source.setName(UUID.randomUUID().toString());
		source.setUrl(UUID.randomUUID().toString()); 
		source.setSelectorContainer(UUID.randomUUID().toString()); 
		source.setSelectorContent(UUID.randomUUID().toString()); 
		source.setSelectorTitle(UUID.randomUUID().toString()); 
		source.setSelectorDescription(UUID.randomUUID().toString()); 
		source.setSelectorImg(UUID.randomUUID().toString()); 
		
		//WHEN
		Source result = sourceRepositoryImpl.saveOrUpdate(source);
		
		//THEN
		assertNotNull(result);
		assertNotNull(result.getId());
		assertTrue(result.getId() instanceof Long);
		assertTrue(result.getId() > 0);
		assertTrue(result.getId().equals(source.getId()));
		assertTrue(result.getName().equals(source.getName()));
		assertTrue(result.getUrl().equals(source.getUrl()));
		assertTrue(result.getSelectorContainer().equals(source.getSelectorContainer()));
		assertTrue(result.getSelectorContent().equals(source.getSelectorContent()));
		assertTrue(result.getSelectorTitle().equals(source.getSelectorTitle()));
		assertTrue(result.getSelectorDescription().equals(source.getSelectorDescription()));
		assertTrue(result.getSelectorImg().equals(source.getSelectorImg()));
		assertNotNull(result.getCreatedAt());
		assertNotNull(result.getUpdatedAt());
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
		source.setUrl(UUID.randomUUID().toString());
		
		//WHEN
		sourceRepositoryImpl.saveOrUpdate(source);		
		
		//THEN
	}
	
	@Test(expected = Exception.class)
	public void saveOrUpdateCreateWithoutUrlTest() {
		//GIVE
		Source source = new Source();
		source.setName(UUID.randomUUID().toString());
		
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
		String nameOld = UUID.randomUUID().toString();
		String urlOld = UUID.randomUUID().toString();
		
		Source source = new Source();
		source.setName(nameOld);
		source.setUrl(urlOld); 
		source.setSelectorContainer(UUID.randomUUID().toString()); 
		source.setSelectorContent(UUID.randomUUID().toString()); 
		source.setSelectorTitle(UUID.randomUUID().toString()); 
		source.setSelectorDescription(UUID.randomUUID().toString()); 
		source.setSelectorImg(UUID.randomUUID().toString());
		sourceRepositoryImpl.saveOrUpdate(source);
		
		String nameUpdated = UUID.randomUUID().toString();
		String urlUpdated = UUID.randomUUID().toString();
		Date dateOld = source.getUpdatedAt();
		
		source.setName(nameUpdated);
		source.setUrl(urlUpdated);
		
		//WHEN
		Source result = sourceRepositoryImpl.saveOrUpdate(source);
		
		//THEN
		assertNotNull(result);
		assertNotNull(result.getId());
		assertTrue(result.getId() instanceof Long);
		assertTrue(result.getId() > 0);
		assertTrue(result.getId().equals(source.getId()));
		assertTrue(result.getName().equals(nameUpdated));
		assertTrue(result.getUrl().equals(urlUpdated));
		assertFalse(result.getName().equals(nameOld));
		assertFalse(result.getUrl().equals(urlOld));
		assertNotNull(result.getCreatedAt());
		assertNotNull(result.getUpdatedAt());
		assertTrue(result.getCreatedAt().equals(source.getCreatedAt()));
		assertFalse(result.getUpdatedAt().equals(dateOld));
	}

	@Test	
	public void getByIdTest() {
		//GIVE
		Source source = new Source();
		source.setName(UUID.randomUUID().toString());
		source.setUrl(UUID.randomUUID().toString()); 
		source.setSelectorContainer(UUID.randomUUID().toString()); 
		source.setSelectorContent(UUID.randomUUID().toString()); 
		source.setSelectorTitle(UUID.randomUUID().toString()); 
		source.setSelectorDescription(UUID.randomUUID().toString()); 
		source.setSelectorImg(UUID.randomUUID().toString());
		sourceRepositoryImpl.saveOrUpdate(source);
		
		//WHEN
		Source result = sourceRepositoryImpl.getById(source.getId());
		
		//THEN
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getCreatedAt());
		assertNotNull(result.getUpdatedAt());
		assertTrue(result.getId() instanceof Long);
		assertTrue(result.getId() > 0);
		assertTrue(result.getId().equals(source.getId()));
		assertTrue(result.getName().equals(source.getName()));
		assertTrue(result.getUrl().equals(source.getUrl()));
		assertTrue(result.getSelectorContainer().equals(source.getSelectorContainer()));
		assertTrue(result.getSelectorContent().equals(source.getSelectorContent()));
		assertTrue(result.getSelectorTitle().equals(source.getSelectorTitle()));
		assertTrue(result.getSelectorDescription().equals(source.getSelectorDescription()));
		assertTrue(result.getSelectorImg().equals(source.getSelectorImg()));
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
		source.setName(UUID.randomUUID().toString());
		source.setUrl(UUID.randomUUID().toString()); 
		source.setSelectorContainer(UUID.randomUUID().toString()); 
		source.setSelectorContent(UUID.randomUUID().toString()); 
		source.setSelectorTitle(UUID.randomUUID().toString()); 
		source.setSelectorDescription(UUID.randomUUID().toString()); 
		source.setSelectorImg(UUID.randomUUID().toString());
		sourceRepositoryImpl.saveOrUpdate(source);
		
		//WHEN
		List<Source> results = sourceRepositoryImpl.getAll();
		
		//THEN
		assertNotNull(results);
		assertFalse(results.isEmpty());
		assertTrue(results.size() == 1);
		Source result = results.get(0);
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getCreatedAt());
			assertNotNull(result.getUpdatedAt());
			assertTrue(result.getId() instanceof Long);
			assertTrue(result.getId() > 0);
			assertTrue(result.getId().equals(source.getId()));
			assertTrue(result.getName().equals(source.getName()));
			assertTrue(result.getUrl().equals(source.getUrl()));
			assertTrue(result.getSelectorContainer().equals(source.getSelectorContainer()));
			assertTrue(result.getSelectorContent().equals(source.getSelectorContent()));
			assertTrue(result.getSelectorTitle().equals(source.getSelectorTitle()));
			assertTrue(result.getSelectorDescription().equals(source.getSelectorDescription()));
			assertTrue(result.getSelectorImg().equals(source.getSelectorImg()));
		
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
	
	@Test
	public void deleteTest() {
		//GIVE
		Source source = new Source();
		source.setName(UUID.randomUUID().toString());
		source.setUrl(UUID.randomUUID().toString());
		source.setSelectorContainer(UUID.randomUUID().toString()); 
		source.setSelectorContent(UUID.randomUUID().toString()); 
		source.setSelectorTitle(UUID.randomUUID().toString()); 
		source.setSelectorDescription(UUID.randomUUID().toString()); 
		source.setSelectorImg(UUID.randomUUID().toString());
		sourceRepositoryImpl.saveOrUpdate(source);
		
		//WHEN
		sourceRepositoryImpl.delete(source);
		Source result = sourceRepositoryImpl.getById(source.getId());
		
		//THEN
		assertNull(result);
	}
	
	@Test(expected=Exception.class)
	public void deleteBySourceNullTest() {
		//GIVE
		
		//WHEN
		sourceRepositoryImpl.delete(null);
		
		//THEN
	}
	
}
