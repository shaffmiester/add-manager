package com.shaffersoft.service;

import com.shaffersoft.model.Advertisement;
import com.shaffersoft.model.Newspaper;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NewspaperServiceTest{

    private NewspaperService newspaperService;
    private static EntityManagerFactory emf;
    private EntityManager em;

    private Newspaper newspaperOne;
    private Newspaper newspaperTwo;

    @BeforeClass
    public static void before(){
        emf = Persistence.createEntityManagerFactory("hsqldb-ds");
    }

    @Before
    public void setUp(){

        newspaperService = new NewspaperService(emf);
        em = emf.createEntityManager();
    }

    @After
    public void tearDown(){
        em.close();
    }

    @Test
    public void testAddNewspaper() throws Exception {

        Newspaper testPaper = newspaperService.addNewspaper("Paper One", "MO");
        assertNotNull(testPaper.getId());

        Newspaper foundPaper = em.find(Newspaper.class, testPaper.getId());
        assertNotNull(foundPaper);
        assertEquals(testPaper.getId(), foundPaper.getId());
        assertEquals(testPaper.getName(), foundPaper.getName());
        assertEquals(testPaper.getState(), foundPaper.getState());


    }

    @Test
    public void testAddAdvertisement() throws Exception {

        String name = "Stuff";
        String text = "This is the best stuff EVER!";
        Advertisement testAdd = newspaperService.addAdvertisement(name, text);
        assertNotNull(testAdd.getId());

        Advertisement foundAdd = em.find(Advertisement.class, testAdd.getId());
        assertNotNull(foundAdd);
        assertEquals(testAdd.getId(), foundAdd.getId());
        assertEquals(name, foundAdd.getName());
        assertEquals(text, foundAdd.getText());


    }

    @Test
    public void testGetNewspapersByids(){
        Newspaper paperOne = newspaperService.addNewspaper("Paper One", "MO");
        Newspaper paperTwo = newspaperService.addNewspaper("Paper Two", "MI");

        List<Long> ids = new ArrayList<Long>();
        ids.add(paperOne.getId());
        ids.add(paperTwo.getId());

        List<Newspaper> newspapers = newspaperService.getNewspapersByIds(ids);
        assertNotNull(newspapers);
        assertFalse(CollectionUtils.isEmpty(newspapers));
    }

    @Test
    public void test(){

    }

    @Test
    public void testGetAllNewspapersWhenNonePresent() throws Exception {
        List<Newspaper> papers = newspaperService.getAllNewspapers();

        assertNotNull(papers);
        assertTrue(CollectionUtils.isEmpty(papers));
    }

    @Test
    public void testGetAllNewspapers() throws Exception {
        newspaperService.addNewspaper("Paper 1", "MI");
        newspaperService.addNewspaper("Paper 2", "IL");

        List<Newspaper> papers = newspaperService.getAllNewspapers();

        assertFalse(CollectionUtils.isEmpty(papers));
        assertEquals(2, papers.size());
    }

    @Test
    public void testGetAllAdvertisements() throws Exception {
        newspaperService.addAdvertisement("Add One", "Sweet Text Goes here");
        newspaperService.addNewspaper("Add Two", "Get it while it's hot");

        List<Advertisement> adds = newspaperService.getAllAdvertisements();

        assertFalse(CollectionUtils.isEmpty(adds));
        //assertEquals(2, adds.size());
    }

    @Test
    public void test2(){
        Newspaper paperOne = newspaperService.addNewspaper("Paper One", "MO");
        Newspaper paperTwo = newspaperService.addNewspaper("Paper Two", "MI");

        List<Long> newspaperIds = new ArrayList<Long>();
        newspaperIds.add(paperOne.getId());
        newspaperIds.add(paperTwo.getId());

        Advertisement testAdd = newspaperService.addAdvertisement("Add One", "Some Text");

        newspaperService.associateAdvertisementWithNewspapers(testAdd.getId(), newspaperIds );
    }
}