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

import com.extracodigo.pcs.entity.Post;
import com.extracodigo.pcs.repository.PostRepository;

public class PostServiceImplTest {
	
	private static PostServiceImpl postServiceImpl;
	
	private static PostRepository postRepository;
	private static Post postResult;
	
	private static final String TITLE = "INTRODUCCION A EXTRACODIGO";
	private static final String URL = "http://extracodigo.blogspot.com";
	private static final String DESCRIPTION = "ESTA ES LA INTRODUCCION A EXTRACODIGO";
	private static final String URL_TO_IMAGE = "http://extracodigo.blogspot.com";
	
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		postResult = new Post();
		postResult.setId(1L);
		postResult.setTitle(TITLE);
		postResult.setUrl(URL);
		postResult.setDescription(DESCRIPTION);
		postResult.setUrlToImage(URL_TO_IMAGE);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		postRepository = Mockito.mock(PostRepository.class);
		
		postServiceImpl = new PostServiceImpl();
		postServiceImpl.setPostRepository(postRepository);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createTest() {
		//GIVE
		Post post = new Post();
		post.setId(1L);
		post.setTitle(TITLE);
		post.setUrl(URL);
		post.setDescription(DESCRIPTION);
		post.setUrlToImage(URL_TO_IMAGE);
		
		Mockito.when(postRepository.saveOrUpdate(post)).thenReturn(postResult);
		
		//WHEN
		Post result = postServiceImpl.create(post);
		
		//THEN		
		assertNotNull(result);
		assertNotNull(result.getId());
		assertTrue(result.getId() instanceof Long);
		assertTrue(result.getTitle().equals(post.getTitle()));
		assertTrue(result.getUrl().equals(post.getUrl()));
		assertTrue(result.getDescription().equals(post.getDescription()));
		assertTrue(result.getUrlToImage().equals(post.getUrlToImage()));
	}
	
	@Test(expected=Exception.class)
	public void createNullTest() {
		//GIVE
		Post post = null;
		
		Mockito.when(postRepository.saveOrUpdate(post)).thenThrow(HibernateException.class);
		
		//WHEN
		postServiceImpl.create(post);
		
		//THEN
	}
	
	@Test
	public void updateTest() {
		//GIVE
		Post post = new Post();
		post.setId(1L);
		
		Mockito.when(postRepository.saveOrUpdate(post)).thenReturn(postResult);
		
		//WHEN
		Post result = postServiceImpl.update(post);
		
		//THEN		
		assertNotNull(result);
		assertNotNull(result.getId());
		assertTrue(result.getId() instanceof Long);
		assertFalse(result.getTitle().equals(post.getTitle()));
		assertFalse(result.getUrl().equals(post.getUrl()));
		assertFalse(result.getDescription().equals(post.getDescription()));
		assertFalse(result.getUrlToImage().equals(post.getUrlToImage()));
	}
	
	@Test(expected=Exception.class)
	public void updateNullTest() {
		//GIVE
		Post post = null;
		
		Mockito.when(postRepository.saveOrUpdate(post)).thenThrow(HibernateException.class);
		
		//WHEN
		postServiceImpl.update(post);
		
		//THEN
	}
	
	@Test
	public void getByIdTest() {
		//GIVE
		Long id = 1L;
		
		Mockito.when(postRepository.getById(id)).thenReturn(postResult);
		
		//WHEN
		Post result = postServiceImpl.getById(id);
		
		//THEN		
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getTitle());
		assertNotNull(result.getUrl());
		assertNotNull(result.getDescription());
		assertNotNull(result.getUrlToImage());
		assertTrue(result.getId() instanceof Long);
	}
	
	@Test
	public void getByAllTest() {
		//GIVE
		
		Mockito.when(postRepository.getAll()).thenReturn(new ArrayList<Post>());
		
		//WHEN
		List<Post> results = postServiceImpl.getAll();
		
		//THEN		
		assertNotNull(results);
	}
	
	@Test
	public void deleteTest() {
		//GIVE
		Post post = new Post();
		post.setId(1L);
		
		Mockito.when(postRepository.getById(post.getId())).thenReturn(post);
		Mockito.doNothing().when(postRepository).delete(post);
		
		//WHEN
		postServiceImpl.delete(1L);
		
		//THEN
		Mockito.verify(postRepository).getById(post.getId());
		Mockito.verify(postRepository).delete(post);
	}
	
	@Test(expected=Exception.class)
	public void deleteByIdNotExistTest() {
		//GIVE
		Long idNotExist = 1L;
		Post post = null;
		
		Mockito.when(postRepository.getById(idNotExist)).thenReturn(post);
		Mockito.doThrow(HibernateException.class).when(postRepository).delete(post);
		
		//WHEN
		postServiceImpl.delete(idNotExist);
		
		//THEN
		Mockito.verify(postRepository).getById(idNotExist);
		Mockito.verify(postRepository).delete(post);
	}

}
