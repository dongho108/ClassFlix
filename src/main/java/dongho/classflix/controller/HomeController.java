package dongho.classflix.controller;

import dongho.classflix.controller.dto.HomeLectureDto;
import dongho.classflix.domain.Lecture;
import dongho.classflix.repository.LectureRepository;
import dongho.classflix.service.LectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final LectureService lectureService;
    private final LectureRepository lectureRepository;

    @RequestMapping("/")
    public String home(Model model) {

//        List<Lecture> lectures = lectureService.findAll();
        List<Lecture> lectures = lectureRepository.findAllPageSort(getDefaultPageRequest());

        List<HomeLectureDto> HomeLectureDtos = new ArrayList<>();

        setHomeLectureDto(lectures, HomeLectureDtos);

        model.addAttribute("lectures", HomeLectureDtos);
        return "home";
    }

    private PageRequest getDefaultPageRequest() {
        return PageRequest.of(0, 16, Sort.Direction.DESC, "createdDate");
    }

    private void setHomeLectureDto(List<Lecture> lectures, List<HomeLectureDto> homeLectureDtos) {
        for (int i = 0; i < lectures.size(); i++) {
            HomeLectureDto lectureDto = new HomeLectureDto();
            lectureDto.setId(lectures.get(i).getId());
            lectureDto.setRepresentImagePath(lectures.get(i).getRepresentImagePath());
            lectureDto.setLectureName(lectures.get(i).getLectureName());
            lectureDto.setAverageRating(lectures.get(i).getAverageRating());

            homeLectureDtos.add(lectureDto);
        }
    }


}
