package com.example.db_hw;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    List<String> owners = new ArrayList();
    MyDB myDB = new MyDB();

    @GetMapping("/")
    public String home(Model model) {
        if (owners.isEmpty()) {
            owners = myDB.getPersons();
        }
        model.addAttribute("owners", owners);
        return "index";
    }
}
