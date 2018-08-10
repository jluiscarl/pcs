package com.extracodigo.pcs.repository.impl;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.extracodigo.pcs.entity.Post;


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

	@Test
	public void saveOrUpdateCreateTest() {
		//GIVE
		Post post = new Post();
		post.setTitle(UUID.randomUUID().toString());
		post.setUrl(UUID.randomUUID().toString());
		post.setDescription(UUID.randomUUID().toString());
		post.setUrlToImage(UUID.randomUUID().toString());
		
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
		assertNotNull(result.getCreatedAt());
		assertNotNull(result.getUpdatedAt());
	}

}
