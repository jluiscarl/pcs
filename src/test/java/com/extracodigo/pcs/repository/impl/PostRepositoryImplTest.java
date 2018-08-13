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

import com.extracodigo.pcs.entity.Post;
import com.extracodigo.pcs.entity.Source;


public class PostRepositoryImplTest {
	
	private static PostRepositoryImpl postRepositoryImpl;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		postRepositoryImpl = new PostRepositoryImpl();
		postRepositoryImpl.setConnection(
				new ConnectionImpl(
						SourceRepositoryImplTest.class.getClassLoader().getResource("hibernate.cfg.xml").getPath()
				)
		);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=Exception.class)
	public void saveOrUpdateCreateWithoutSourceTest() {
		//GIVE
		Post post = new Post();
		post.setTitle(UUID.randomUUID().toString());
		post.setUrl(UUID.randomUUID().toString());
		post.setDescription(UUID.randomUUID().toString());
		post.setUrlToImage(UUID.randomUUID().toString());
		
		//WHEN
		postRepositoryImpl.saveOrUpdate(post);
		
		//THEN
	}
	
	@Test
	public void saveOrUpdateCreateWithNewSourceTest() {
		//GIVE
		Source source = new Source();
		source.setName(UUID.randomUUID().toString());
		source.setUrl(UUID.randomUUID().toString());
		source.setSelectorContainer(UUID.randomUUID().toString()); 
		source.setSelectorContent(UUID.randomUUID().toString()); 
		source.setSelectorTitle(UUID.randomUUID().toString()); 
		source.setSelectorDescription(UUID.randomUUID().toString()); 
		source.setSelectorImg(UUID.randomUUID().toString());
		
		Post post = new Post();
		post.setTitle(UUID.randomUUID().toString());
		post.setUrl(UUID.randomUUID().toString());
		post.setDescription(UUID.randomUUID().toString());
		post.setUrlToImage(UUID.randomUUID().toString());
		post.setSource(source);
		
		//WHEN
		Post result = postRepositoryImpl.saveOrUpdate(post);
		
		//THEN
		assertNotNull(result);
		assertNotNull(result.getId());
		assertTrue(result.getId() instanceof Long);
		assertTrue(result.getId() > 0);
		assertTrue(result.getId().equals(post.getId()));
		assertTrue(result.getTitle().equals(post.getTitle()));
		assertTrue(result.getUrl().equals(post.getUrl()));
		assertTrue(result.getDescription().equals(post.getDescription()));
		assertTrue(result.getUrlToImage().equals(post.getUrlToImage()));
		assertNotNull(result.getSource());
		assertTrue(result.getSource().equals(post.getSource()));
		assertNotNull(result.getCreatedAt());
		assertNotNull(result.getUpdatedAt());
	}
	
	@Test(expected=Exception.class)
	public void saveOrUpdateCreateWithInvalidSourceTest() {
		//GIVE
		Source source = new Source();
		
		Post post = new Post();
		post.setTitle(UUID.randomUUID().toString());
		post.setUrl(UUID.randomUUID().toString());
		post.setDescription(UUID.randomUUID().toString());
		post.setUrlToImage(UUID.randomUUID().toString());
		post.setSource(source);
		
		//WHEN
		postRepositoryImpl.saveOrUpdate(post);
		
		//THEN
	}
	
	@Test(expected=Exception.class)
	public void saveOrUpdateCreateWithInvalidPostTest() {
		//GIVE
		Post post = new Post();
		
		//WHEN
		postRepositoryImpl.saveOrUpdate(post);
		
		//THEN
	}
	
	@Test(expected=Exception.class)
	public void saveOrUpdateWithPostNullTest() {
		//GIVE
		
		Post post = null;
		
		//WHEN
		postRepositoryImpl.saveOrUpdate(post);
		
		//THEN
	}
	
	@Test(expected=Exception.class)
	public void saveOrUpdateCreateWithoutTitleTest() {
		//GIVE
		Source source = new Source();
		source.setName(UUID.randomUUID().toString());
		source.setUrl(UUID.randomUUID().toString());
		
		Post post = new Post();

		post.setUrl(UUID.randomUUID().toString());
		post.setDescription(UUID.randomUUID().toString());
		post.setUrlToImage(UUID.randomUUID().toString());
		post.setSource(source);
		
		//WHEN
		postRepositoryImpl.saveOrUpdate(post);
		
		//THEN
	}

	@Test(expected=Exception.class)
	public void saveOrUpdateCreateWithoutUrlTest() {
		//GIVE
		Source source = new Source();
		source.setName(UUID.randomUUID().toString());
		source.setUrl(UUID.randomUUID().toString());
		
		Post post = new Post();

		post.setTitle(UUID.randomUUID().toString());
		post.setDescription(UUID.randomUUID().toString());
		post.setUrlToImage(UUID.randomUUID().toString());
		post.setSource(source);
		
		//WHEN
		postRepositoryImpl.saveOrUpdate(post);
		
		//THEN
	}
	
	@Test(expected=Exception.class)
	public void saveOrUpdateCreateWithoutDescriptionTest() {
		//GIVE
		Source source = new Source();
		source.setName(UUID.randomUUID().toString());
		source.setUrl(UUID.randomUUID().toString());
		
		Post post = new Post();

		post.setTitle(UUID.randomUUID().toString());
		post.setUrl(UUID.randomUUID().toString());
		post.setUrlToImage(UUID.randomUUID().toString());
		post.setSource(source);
		
		//WHEN
		postRepositoryImpl.saveOrUpdate(post);
		
		//THEN
	}
	
	@Test(expected=Exception.class)
	public void saveOrUpdateCreateWithoutUrlToImageTest() {
		//GIVE
		Source source = new Source();
		source.setName(UUID.randomUUID().toString());
		source.setUrl(UUID.randomUUID().toString());
		
		Post post = new Post();

		post.setTitle(UUID.randomUUID().toString());
		post.setUrl(UUID.randomUUID().toString());
		post.setDescription(UUID.randomUUID().toString());
		post.setSource(source);
		
		//WHEN
		postRepositoryImpl.saveOrUpdate(post);
		
		//THEN
	}
	
	@Test
	public void saveOrUpdateUpdateTest() {
		//GIVE
		Source source = new Source();
		source.setName(UUID.randomUUID().toString());
		source.setUrl(UUID.randomUUID().toString());
		source.setSelectorContainer(UUID.randomUUID().toString()); 
		source.setSelectorContent(UUID.randomUUID().toString()); 
		source.setSelectorTitle(UUID.randomUUID().toString()); 
		source.setSelectorDescription(UUID.randomUUID().toString()); 
		source.setSelectorImg(UUID.randomUUID().toString());
		
		String titleOld = UUID.randomUUID().toString();
		
		Post post = new Post();
		post.setTitle(titleOld);
		post.setUrl(UUID.randomUUID().toString());
		post.setDescription(UUID.randomUUID().toString());
		post.setUrlToImage(UUID.randomUUID().toString());
		post.setSource(source);
		postRepositoryImpl.saveOrUpdate(post);
		
		String titleUpdate = UUID.randomUUID().toString();
		Date dateOld = post.getUpdatedAt();
		
		post.setTitle(titleUpdate);
		
		//WHEN
		Post result = postRepositoryImpl.saveOrUpdate(post);
		
		//THEN
		assertNotNull(result);
		assertNotNull(result.getId());
		assertTrue(result.getId() instanceof Long);
		assertTrue(result.getId() > 0);
		assertTrue(result.getId().equals(post.getId()));
		assertTrue(result.getTitle().equals(titleUpdate));
		assertFalse(result.getTitle().equals(titleOld));
		assertNotNull(result.getCreatedAt());
		assertNotNull(result.getUpdatedAt());
		assertTrue(result.getCreatedAt().equals(post.getCreatedAt()));
		assertFalse(result.getUpdatedAt().equals(dateOld));
	}
	
	@Test
	public void saveOrUpdateCreateWithExistSourceTest() {
		//GIVE
		
		SourceRepositoryImpl sourceRepositoryImpl = new SourceRepositoryImpl();
		sourceRepositoryImpl.setConnection(
				new ConnectionImpl(
						SourceRepositoryImplTest.class.getClassLoader().getResource("hibernate.cfg.xml").getPath()
				)
		);
		
		Source source = new Source();
		source.setName(UUID.randomUUID().toString());
		source.setUrl(UUID.randomUUID().toString());
		source.setSelectorContainer(UUID.randomUUID().toString()); 
		source.setSelectorContent(UUID.randomUUID().toString()); 
		source.setSelectorTitle(UUID.randomUUID().toString()); 
		source.setSelectorDescription(UUID.randomUUID().toString()); 
		source.setSelectorImg(UUID.randomUUID().toString());
		sourceRepositoryImpl.saveOrUpdate(source);
		
		
		
		Post post = new Post();
		post.setTitle(UUID.randomUUID().toString());
		post.setUrl(UUID.randomUUID().toString());
		post.setDescription(UUID.randomUUID().toString());
		post.setUrlToImage(UUID.randomUUID().toString());
		post.setSource(source);
		
		//WHEN
		Post result = postRepositoryImpl.saveOrUpdate(post);
		
		//THEN
		assertNotNull(result);
		assertNotNull(result.getId());
		assertTrue(result.getId() instanceof Long);
		assertTrue(result.getId() > 0);
		assertTrue(result.getId().equals(post.getId()));
		assertTrue(result.getTitle().equals(post.getTitle()));
		assertTrue(result.getUrl().equals(post.getUrl()));
		assertTrue(result.getDescription().equals(post.getDescription()));
		assertTrue(result.getUrlToImage().equals(post.getUrlToImage()));
		assertNotNull(result.getSource());
		assertTrue(result.getSource().equals(post.getSource()));
		assertNotNull(result.getCreatedAt());
		assertNotNull(result.getUpdatedAt());
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
		
		Post post = new Post();
		post.setTitle(UUID.randomUUID().toString());
		post.setUrl(UUID.randomUUID().toString());
		post.setDescription(UUID.randomUUID().toString());
		post.setUrlToImage(UUID.randomUUID().toString());
		post.setSource(source);
		
		postRepositoryImpl.saveOrUpdate(post);
		
		//WHEN
		Post result = postRepositoryImpl.getById(post.getId());
		
		//THEN
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getCreatedAt());
		assertNotNull(result.getUpdatedAt());
		assertTrue(result.getId() instanceof Long);
		assertTrue(result.getId() > 0);
		assertTrue(result.getId().equals(post.getId()));
		assertTrue(result.getTitle().equals(post.getTitle()));
		assertTrue(result.getUrl().equals(post.getUrl()));
		assertTrue(result.getDescription().equals(post.getDescription()));
		assertTrue(result.getUrlToImage().equals(post.getUrlToImage()));
		assertNotNull(result.getSource());
	}
	
	@Test
	public void getByIdNotExistOrIdInvalidTest() {
		//GIVE
		
		//WHEN
		Post result = postRepositoryImpl.getById(Mockito.anyLong());
		
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
		
		Post post = new Post();
		post.setTitle(UUID.randomUUID().toString());
		post.setUrl(UUID.randomUUID().toString());
		post.setDescription(UUID.randomUUID().toString());
		post.setUrlToImage(UUID.randomUUID().toString());
		post.setSource(source);
		
		postRepositoryImpl.saveOrUpdate(post);
		
		//WHEN
		List<Post> results = postRepositoryImpl.getAll();
		
		//THEN
		assertNotNull(results);
		assertFalse(results.isEmpty());
		assertTrue(results.size() == 1);
		Post result = results.get(0);
			assertNotNull(result);
			assertNotNull(result.getId());
			assertTrue(result.getId() instanceof Long);
			assertTrue(result.getId() > 0);
			assertTrue(result.getId().equals(post.getId()));
			assertTrue(result.getTitle().equals(post.getTitle()));
			assertTrue(result.getUrl().equals(post.getUrl()));
			assertTrue(result.getDescription().equals(post.getDescription()));
			assertTrue(result.getUrlToImage().equals(post.getUrlToImage()));
			assertNotNull(result.getSource());
			assertNotNull(result.getCreatedAt());
			assertNotNull(result.getUpdatedAt());
		
	}
	
	@Test
	public void getAllEmptyTest() {
		//GIVE
		
		//WHEN
		List<Post> results = postRepositoryImpl.getAll();
		
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
		
		Post post = new Post();
		post.setTitle(UUID.randomUUID().toString());
		post.setUrl(UUID.randomUUID().toString());
		post.setDescription(UUID.randomUUID().toString());
		post.setUrlToImage(UUID.randomUUID().toString());
		post.setSource(source);
		
		postRepositoryImpl.saveOrUpdate(post);
		
		//WHEN
		postRepositoryImpl.delete(post);
		Post result = postRepositoryImpl.getById(post.getId());
		
		//THEN
		assertNull(result);
	}
	
	@Test(expected=Exception.class)
	public void deleteBySourceNullTest() {
		//GIVE
		
		//WHEN
		postRepositoryImpl.delete(null);
		
		//THEN
	}
}
