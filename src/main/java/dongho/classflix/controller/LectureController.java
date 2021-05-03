package dongho.classflix.controller;

import dongho.classflix.controller.dto.LectureInfoDto;
import dongho.classflix.controller.dto.LectureInfoMemberDto;
import dongho.classflix.domain.Lecture;
import dongho.classflix.domain.Member;
import dongho.classflix.service.LectureService;
import dongho.classflix.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LectureController {

    private final LectureService lectureService;
    private final MemberService memberService;

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

    @GetMapping("/lectures/{lectureId}")
    public String lectureInfo(@PathVariable("lectureId") Long lectureId, Model lectureModel, Model memberModel, Model reviewModel) {
        Lecture lecture = lectureService.findById(lectureId);
        LectureInfoDto lectureInfoDto = new LectureInfoDto();

        setLectureInfoDto(lecture, lectureInfoDto);
        lectureModel.addAttribute("lectureDto", lectureInfoDto);

        List<LectureInfoMemberDto> lectureInfoMemberDtos = new ArrayList<>();
        List<Member> members = memberService.findMembers();

        for (int i = 0; i < members.size(); i++) {
            LectureInfoMemberDto lectureInfoMemberDto = new LectureInfoMemberDto();
            lectureInfoMemberDto.setUserName(members.get(i).getUserName());
            lectureInfoMemberDtos.add(lectureInfoMemberDto);
        }

        memberModel.addAttribute("memberDtos", lectureInfoMemberDtos);

        reviewModel.addAttribute("reviewForm", new ReviewForm());

        return "lectures/lecture";
    }

    @PostMapping("/lecutres/{lectureId}")
//    private String createReview(@PathVariable("lectureId") Long lectureId, ) {
//
//    }

    private void setLectureInfoDto(Lecture lecture, LectureInfoDto lectureInfoDto) {

        lectureInfoDto.setAverageRating(lecture.getAverageRating());
        lectureInfoDto.setContent(lecture.getContent());
        lectureInfoDto.setLectureName(lecture.getLectureName());
        lectureInfoDto.setReviewNum(lecture.getReviewNum());
        lectureInfoDto.setSiteName(lecture.getSiteName());
        lectureInfoDto.setTeacherName(lecture.getTeacherName());
        if (lecture.getUri() != null) {
            lectureInfoDto.setUri(lecture.getUri().toString());
        }
    }
}
