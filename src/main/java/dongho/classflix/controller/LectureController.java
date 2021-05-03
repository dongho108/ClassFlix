package dongho.classflix.controller;

import dongho.classflix.domain.Lecture;
import dongho.classflix.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @GetMapping("/lectures/new")
    public String createForm(Model model) {
        model.addAttribute("lectureForm", new LectureForm());
        return "lectures/lectureForm";
    }

    @PostMapping("/lectures/new")
    public String create(@Valid LectureForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "lectures/lectureForm";
        }
        Lecture lecture = new Lecture(form.getLectureName(), form.getTeacherName(), form.getContent(), form.getRepresentImage(), form.getSiteName(), form.getUri());
        lectureService.join(lecture);
        return "redirect:/";
    }

//    @GetMapping("/lectures/{lectureId}")
//    public String lectureInfo(@PathVariable("lectureId") Long lectureId) {
//        Lecture lecture = lectureService.findById(lectureId);
//
//    }
}
