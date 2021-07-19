package dongho.classflix.controller;

import dongho.classflix.controller.dto.HomeLectureDto;
import dongho.classflix.controller.dto.PageDto;
import dongho.classflix.repository.LectureRepository;
import dongho.classflix.service.LectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final LectureService lectureService;
    private final LectureRepository lectureRepository;

    @RequestMapping("/")
    public String home(Model model, @PageableDefault(size = 16, sort = "createdDate",direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("home controller");

//        List<Lecture> lectures = lectureService.findAll();
//        List<Lecture> lectures = lectureRepository.findAllPageSort(getDefaultPageRequest());
//        setHomeLectureDto(lectures, HomeLectureDtos);

        Page<HomeLectureDto> results = lectureRepository.findAllPageSort(pageable);
        model.addAttribute("lectures", results.getContent());

        log.info(pageable.toString());
        PageDto pageDto = new PageDto(results.getTotalElements(), pageable);

        model.addAttribute("page", pageDto);
        log.info(pageDto.toString());

        return "home";
    }

//    private void setHomeLectureDto(List<Lecture> lectures, List<HomeLectureDto> homeLectureDtos) {
//        for (int i = 0; i < lectures.size(); i++) {
//            HomeLectureDto lectureDto = new HomeLectureDto();
//            lectureDto.setId(lectures.get(i).getId());
//            lectureDto.setRepresentImagePath(lectures.get(i).getRepresentImagePath());
//            lectureDto.setLectureName(lectures.get(i).getLectureName());
//            lectureDto.setAverageRating(lectures.get(i).getAverageRating());
//
//            homeLectureDtos.add(lectureDto);
//        }
//    }


}
