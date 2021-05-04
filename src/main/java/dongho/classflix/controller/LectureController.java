package dongho.classflix.controller;

import dongho.classflix.controller.dto.LectureInfoDto;
import dongho.classflix.controller.dto.LectureInfoMemberDto;
import dongho.classflix.controller.dto.ReviewDto;
import dongho.classflix.domain.Lecture;
import dongho.classflix.domain.Member;
import dongho.classflix.domain.Review;
import dongho.classflix.service.LectureService;
import dongho.classflix.service.MemberService;
import dongho.classflix.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LectureController {

    private final LectureService lectureService;
    private final MemberService memberService;
    private final ReviewService reviewService;

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
    public String lectureInfo(@PathVariable("lectureId") Long lectureId, Model lectureModel, Model memberModel, Model reviewFormModel, Model reviewModel) {
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
        reviewFormModel.addAttribute("reviewForm", new ReviewForm());

        List<Review> reviews = reviewService.findByLecture(lectureId);
        List<ReviewDto> reviewDtos = new ArrayList<>();
        for (int i = 0; i < reviews.size(); i++) {
            ReviewDto reviewDto = new ReviewDto();
            reviewDto.setMemberName(reviews.get(i).getMember().getUserName());
            reviewDto.setContent(reviews.get(i).getContent());
            reviewDto.setPassword(reviews.get(i).getPassword());
            reviewDto.setRating(reviews.get(i).getRating());
            reviewDto.setReviewDate(reviews.get(i).getReviewDate());
            reviewDtos.add(reviewDto);
        }
        reviewModel.addAttribute("reviewDtos", reviewDtos);

        return "lectures/lecture";
    }

    @PostMapping("/lectures/{lectureId}")
    private String createReview(@PathVariable("lectureId") Long lectureId, ReviewForm reviewForm, RedirectAttributes redirectAttributes) {
        Member member = memberService.findByName(reviewForm.getMemberName()).get(0);
        Lecture lecture = lectureService.findById(lectureId);
        Review review = new Review(member, reviewForm.getPassword(), reviewForm.getContent(), reviewForm.getRating(), lecture, LocalDateTime.now());
        reviewService.create(review);

        redirectAttributes.addAttribute("lectureId", lectureId);
        return "redirect:/lectures/{lectureId}";
    }

    private void setLectureInfoDto(Lecture lecture, LectureInfoDto lectureInfoDto) {

        lectureInfoDto.setAverageRating((int) Math.round(lecture.getAverageRating()));
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
