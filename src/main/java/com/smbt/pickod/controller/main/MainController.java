package com.smbt.pickod.controller.main;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {

    @GetMapping("/main")
    public String mainPage() {
        return "main/main";
    }
}
