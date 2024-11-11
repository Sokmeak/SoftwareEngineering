package com.springc.spring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/tp04")

public class TaskController {

    @GetMapping("task1")
    public String getTask(Model model) {
        model.addAttribute("username", "Saren Sokmeak");
        return "task1";
    }

}
