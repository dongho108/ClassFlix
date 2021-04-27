package dongho.classflix.controller;

import dongho.classflix.service.LectureService;
import dongho.classflix.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private LectureService lectureService;

    @RequestMapping("/")
    public String home() {
        return "home";
    }
}
