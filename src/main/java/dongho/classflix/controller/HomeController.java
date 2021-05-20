package dongho.classflix.controller;

import dongho.classflix.controller.dto.HomeLectureDto;
import dongho.classflix.domain.Lecture;
import dongho.classflix.service.LectureService;
import dongho.classflix.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final LectureService lectureService;

    @RequestMapping("/")
    public String home(Model model) {
        List<Lecture> lectures = lectureService.findAll();

        List<HomeLectureDto> HomeLectureDtos = new ArrayList<>();

        for (int i = 0; i < lectures.size(); i++) {
            HomeLectureDto lectureDto = new HomeLectureDto();
            lectureDto.setId(lectures.get(i).getId());
            lectureDto.setRepresentImagePath(lectures.get(i).getRepresentImagePath());
//            lectureDto.setRepresentImagePath("/images/springInstroduction.png");
            lectureDto.setLectureName(lectures.get(i).getLectureName());
            lectureDto.setAverageRating(lectures.get(i).getAverageRating());

            HomeLectureDtos.add(lectureDto);
        }

        model.addAttribute("lectures", HomeLectureDtos);
        return "home";
    }


}
