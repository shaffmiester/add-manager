package com.shaffersoft.controller;

import com.shaffersoft.model.Advertisement;
import com.shaffersoft.model.Newspaper;
import com.shaffersoft.service.NewspaperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by joelshaffer on 8/2/14.
 */
@Controller
public class AddManagerController {

    private NewspaperService newspaperService;

    public AddManagerController(){
        newspaperService = new NewspaperService(Persistence.createEntityManagerFactory("hsqldb-ds"));
    }

    public void setNewspaperService(NewspaperService newspaperService){
        this.newspaperService = newspaperService;
    }

    @RequestMapping("/")
    public String loadHomePage(Model m) {
        m.addAttribute("newspapers", newspaperService.getAllNewspapers());
        m.addAttribute("advertisements", newspaperService.getAllAdvertisements());
        return "index";
    }

    @RequestMapping(value="/newspaper", method= RequestMethod.POST)
    public String addNewsPaper(Model m, @RequestParam(value="name") String name, @RequestParam(value="state") String state) {
        newspaperService.addNewspaper(name, state);
        return "redirect:/";
    }

    @RequestMapping(value="/addNewspaper", method=RequestMethod.GET)
    public String displayAddNewspaper(){
        return "addNewspaper";
    }

    @RequestMapping(value="/addAdvertisement", method=RequestMethod.GET)
    public String displayAddAdvertisement(){
        return "addAdvertisement";
    }

    @RequestMapping(value="/advertisement", method= RequestMethod.POST)
    public String addAdvertisement(Model m, RedirectAttributes redirectAttributes, @RequestParam(value="name") String name, @RequestParam(value="text") String text) {
        newspaperService.addAdvertisement(name, text);
        return "redirect:/";
    }

    @RequestMapping(value = "/addToNewspaper/{id}", method = RequestMethod.GET)
    public String addAvertisementToNewspaper(Model m, @PathVariable Long id){
        Advertisement advertisement = newspaperService.getAdvertisementById(id);
        m.addAttribute("advertisement", advertisement);
        List<Newspaper> newspapers = newspaperService.getAllNewspapers();
        m.addAttribute("newspapers", newspapers);
        return "addAdvertisementToNewspaper";
    }

    @RequestMapping(value = "/addToNewspaper/{id}", method = RequestMethod.POST)
    public String associateAvertisementToNewspaper(Model m, @PathVariable Long id, @RequestParam(value="newspaperIds") List<Long> newspaperIds){
        newspaperService.associateAdvertisementWithNewspapers(id, newspaperIds);
        return "redirect:/";
    }



}
