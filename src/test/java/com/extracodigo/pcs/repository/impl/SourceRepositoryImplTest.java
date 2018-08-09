package com.extracodigo.pcs.repository.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.extracodigo.pcs.entity.Source;

public class SourceRepositoryImplTest {
	
	private static SourceRepositoryImpl sourceRepositoryImpl;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sourceRepositoryImpl = new SourceRepositoryImpl();
		sourceRepositoryImpl.setConnection(new ConnectionImpl());
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
	public void createTest() {
		Source source = new Source();
		source.setName("Test");
		source.setUrl("url");
		Source result = sourceRepositoryImpl.create(source);
		
		System.out.println(result);
		
		assertNotNull(result);
		assertNotNull(result.getId());
		assertTrue(result.getId() > 0);
	}

}
