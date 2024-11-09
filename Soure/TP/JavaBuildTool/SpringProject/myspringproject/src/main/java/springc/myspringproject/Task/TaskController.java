package springc.myspringproject.Task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/TP04")
public class TaskController {
    @GetMapping("/task01")
    public String getTask(Model model) {
        model.addAttribute("username", "Sokmeak Saren");
        return "Task";
    }
}
