package com.shaffersoft.controller;

import com.shaffersoft.model.Advertisement;
import com.shaffersoft.model.Newspaper;
import com.shaffersoft.service.NewspaperService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddManagerControllerTest {

    @Mock private NewspaperService newspaperService;
    @Mock private Model model;
    @Mock private RedirectAttributes redirectAttributes;

    private AddManagerController addManagerController;

    private Advertisement sampleAddvertisement;
    private Newspaper sampleNewspaper;



    @Before
    public void setUp() throws Exception {
        addManagerController = new AddManagerController();
        addManagerController.setNewspaperService(newspaperService);

        sampleAddvertisement = new Advertisement();
        sampleAddvertisement.setId(123L);
        sampleAddvertisement.setName("Test Add");
        sampleAddvertisement.setText("Sample Text");

        sampleNewspaper = new Newspaper();
        sampleNewspaper.setId(456L);
        sampleNewspaper.setName("Test Paper");
        sampleNewspaper.setState("MO");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testLoadHomePage() throws Exception {
       List<Newspaper> newspapers = new ArrayList<Newspaper>();
       when(newspaperService.getAllNewspapers()).thenReturn(newspapers);
       List<Advertisement> advertisements = new ArrayList<Advertisement>();
       when(newspaperService.getAllAdvertisements()).thenReturn(advertisements);

       String view = addManagerController.loadHomePage(model);

       assertEquals("index", view);
       verify(newspaperService).getAllNewspapers();
       verify(newspaperService).getAllAdvertisements();
       verify(model).addAttribute(eq("newspapers"), eq(newspapers));
       verify(model).addAttribute(eq("advertisements"), eq(advertisements));
    }

    @Test
    public void testAddNewsPaper() throws Exception {
        String name = "paper";
        String state = "MO";
        String view = addManagerController.addNewsPaper(model, name, state);

        assertEquals("redirect:/", view);
        verify(newspaperService).addNewspaper(eq(name), eq(state));
    }

    @Test
    public void testDisplayAddNewspaper() throws Exception {
        String view = addManagerController.displayAddNewspaper();

        assertEquals("addNewspaper", view);
    }

    @Test
    public void testDisplayAddAdvertisement() throws Exception {
        String view = addManagerController.displayAddAdvertisement();

        assertEquals("addAdvertisement", view);
    }

    @Test
    public void testAddAdvertisement() throws Exception {
        String name = "Add";
        String text = "Some Text";
        String view = addManagerController.addAdvertisement(model, redirectAttributes, name, text );

        verify(newspaperService).addAdvertisement(eq(name), eq(text));
        assertEquals("redirect:/", view);
    }

    @Test
    public void testAddAvertisementToNewspaper() throws Exception {
        when(newspaperService.getAdvertisementById(anyLong())).thenReturn(sampleAddvertisement);
        List<Newspaper> newspapers = new ArrayList<Newspaper>();
        newspapers.add(sampleNewspaper);
        when(newspaperService.getAllNewspapers()).thenReturn(newspapers);
        Long addId = new Long("123");

        String view = addManagerController.addAvertisementToNewspaper(model, addId);

        verify(newspaperService).getAdvertisementById(anyLong());
        verify(newspaperService).getAllNewspapers();
        verify(model).addAttribute(eq("advertisement"), eq(sampleAddvertisement));
        verify(model).addAttribute(eq("newspapers"), eq(newspapers));
        assertEquals("addAdvertisementToNewspaper", view);
    }

    @Test
    public void testAssociateAvertisementToNewspaper() throws Exception {
        Long addId = new Long(123);
        List<Long> newspaperIds = new ArrayList<Long>();
        Long paperOneId = new Long(222);
        Long paperTwoId = new Long(333);
        newspaperIds.add(paperOneId);
        newspaperIds.add(paperTwoId);

        String view = addManagerController.associateAvertisementToNewspaper(model, addId, newspaperIds);

        verify(newspaperService).associateAdvertisementWithNewspapers(eq(addId), same(newspaperIds));
        assertEquals("redirect:/", view);
    }
}