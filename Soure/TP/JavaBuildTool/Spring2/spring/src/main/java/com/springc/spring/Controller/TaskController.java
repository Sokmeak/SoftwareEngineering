package com.springc.spring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/tp04")

public class TaskController {

    @GetMapping("task1")
    public String getTask(Model model) {
        model.addAttribute("username", "Saren Sokmeak");
        return "task1";
    }

    @GetMapping("task2")
    public String signInForm(Model model) {
        
        return "task2";
    }


    @GetMapping("task3")
    public String signUpForm(Model model) {
        
        return "task3";
    }

 @GetMapping("test")
 public String Test(Model model) {
     return "test";
 }
 


}
