package dongho.classflix.controller;

import dongho.classflix.controller.dto.LectureForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LectureController {

    @GetMapping("/lectures/new")
    public String createForm(Model model) {
        model.addAttribute("lectureForm", new LectureForm());
        return "lectures/lectureForm";
    }

    @PostMapping("/lectures/new")
    public
}
