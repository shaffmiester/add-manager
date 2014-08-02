package com.shaffersoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by joelshaffer on 8/2/14.
 */
@Controller
public class AddManagerController {

    @RequestMapping("/")
    public String loadHomePage(Model m) {
        m.addAttribute("name", "Joel Shaffer");
        return "index";
    }

}
