package dongho.classflix.controller;

import dongho.classflix.controller.dto.HomeLectureDto;
import dongho.classflix.controller.dto.LectureSearchCondition;
import dongho.classflix.controller.dto.PageDto;
import dongho.classflix.controller.dto.SortParams;
import dongho.classflix.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final LectureRepository lectureRepository;

    @ModelAttribute("sortParams")
    public List<SortParams> sortParams() {
        List<SortParams> sortParams = new ArrayList<>();
        sortParams.add(new SortParams("createdDate,DESC", "최신순"));
        sortParams.add(new SortParams("lectureName,ASC", "이름순"));
        return sortParams;
    }


    @RequestMapping("/")
    public String home(Model model, LectureSearchCondition condition, @PageableDefault(size = 16, sort = "createdDate",direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("home controller");

        Page<HomeLectureDto> results = lectureRepository.searchPageSort(condition, pageable);
        model.addAttribute("lectures", results.getContent());

        log.info(pageable.toString());
        log.info(""+pageable.getSort());
        PageDto pageDto = new PageDto(results.getTotalElements(), pageable);

        model.addAttribute("page", pageDto);
        log.info(pageDto.toString());

        log.info(condition.toString());
        model.addAttribute("condition", condition);

        return "home";
    }

}
