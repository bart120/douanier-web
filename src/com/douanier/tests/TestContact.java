package com.douanier.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.douanier.dao.ContactDAO;
import com.douanier.model.Contact;

public class TestContact {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void testListerContacts() {
		ArrayList<Contact> result = ContactDAO.listerContacts();
		if(result.size() <= 0) {
			fail();
		}
	}

	@Test
	public void testGetById()  {
		Contact c = ContactDAO.getById(1);
		assertNotNull(c);
		
		c = ContactDAO.getById(10000);
		assertNull(c);
	}
	
	@Test
	public void testEnregistrer() {
		int nbr = ContactDAO.listerContacts().size();
		Contact c = new Contact();
		c.setNom("test");
		c.setDateNaissance(new Date());
		ContactDAO.enregistrer(c);
		int nbrAfter = ContactDAO.listerContacts().size();
		//assertNotEquals(nbr, nbrAfter);
		assertEquals(nbr+1, nbrAfter);
	}

}
