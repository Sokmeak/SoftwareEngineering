package com.springc.spring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("task4")
    public String getUpate(Model model) {
        return "task4";
    }

    // Dashboard view (Task 5)

    // Task 2: Display the login form
    // @GetMapping("task2")
    // public String loginForm(Model model) {
    // return "task2"; // This should match your login template file (login.html)
    // }

    // Task 5: Handle the login form submission
    @PostMapping("/TP04/task5")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {

        if ("sokmeak".equals(username) && "123456".equals(password)) {
            return "redirect:/tp04/dashboard";
        }
        return "redirect:/tp04/task2"; // Redirect back to login form if login fails
    }

    // Dashboard view (Task 5)
    @GetMapping("dashboard")
    public String dashboard(Model model) {
        return "dashboard"; // This should match your dashboard template file (dashboard.html)
    }

}
