package dongho.classflix.controller;

import dongho.classflix.controller.dto.LectureInfoDto;
import dongho.classflix.controller.dto.LectureInfoMemberDto;
import dongho.classflix.controller.dto.ReviewDto;
import dongho.classflix.domain.Lecture;
import dongho.classflix.domain.Member;
import dongho.classflix.domain.Review;
import dongho.classflix.service.FileInfo;
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
        log.info("check");

        FileInfo fileInfo = lectureService.fileParser(form.getImage());

        Lecture lecture = new Lecture(form.getLectureName(), form.getTeacherName(),
                form.getContent(), fileInfo.getFilePath(), fileInfo.getFileSize(), fileInfo.getFileName(), form.getSiteName(), form.getUri(), LocalDateTime.now());

        Long testId = lectureService.join(lecture);
        log.info("{}", testId);

        return "redirect:/";
    }

    @GetMapping("/lectures/{lectureId}")
    public String lectureInfo(@PathVariable("lectureId") Long lectureId, Model lectureModel, Model memberModel, Model reviewFormModel, Model reviewModel, Model reviewEdit) {
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
        setReviewDtos(reviews, reviewDtos);
        reviewModel.addAttribute("reviewDtos", reviewDtos);

        reviewEdit.addAttribute("reviewEdit", new ReviewEdit());

        return "lectures/lecture";
    }

    private void setReviewDtos(List<Review> reviews, List<ReviewDto> reviewDtos) {
        for (int i = 0; i < reviews.size(); i++) {
            ReviewDto reviewDto = new ReviewDto();
            reviewDto.setReviewId(reviews.get(i).getId());
            reviewDto.setMemberName(reviews.get(i).getMember().getUserName());
            reviewDto.setContent(reviews.get(i).getContent());
            reviewDto.setRating(reviews.get(i).getRating());
            reviewDto.setReviewDate(reviews.get(i).getReviewDate());
            reviewDtos.add(reviewDto);
        }
    }

    @PostMapping("/lectures/{lectureId}")
    private String createReview(@PathVariable("lectureId") Long lectureId, RedirectAttributes redirectAttributes, @Valid ReviewForm reviewForm, BindingResult result) {

        redirectAttributes.addAttribute("lectureId", lectureId);

        if (result.hasErrors()) {
            log.info("check");
            return "redirect:/members/new";
        }

        Member member = memberService.findByName(reviewForm.getMemberName()).get(0);
        Lecture lecture = lectureService.findById(lectureId);
        Review review = new Review(member, reviewForm.getContent(), reviewForm.getRating(), lecture, LocalDateTime.now());
        reviewService.create(review);

        return "redirect:/lectures/{lectureId}";
    }

    @PostMapping("/lectures/{lectureId}/updateReview/{reviewId}")
    private String updateReview(@PathVariable("lectureId") Long lectureId, @PathVariable("reviewId") Long reviewId,
                                       ReviewForm form, RedirectAttributes redirectAttributes) {

        reviewService.update(reviewId, lectureId, form.getContent(), form.getRating());
        redirectAttributes.addAttribute("lectureId", lectureId);
        return "redirect:/lectures/{lectureId}";
    }

    @PostMapping("/lectures/{lectureId}/removeReview/{reviewId}")
    private String removeReview(@PathVariable("lectureId") Long lectureId, @PathVariable("reviewId") Long reviewId, RedirectAttributes redirectAttributes) {
        reviewService.delete(reviewId, lectureId);
        redirectAttributes.addAttribute("lectureId", lectureId);
        return "redirect:/lectures/{lectureId}";
    }

    private void setLectureInfoDto(Lecture lecture, LectureInfoDto lectureInfoDto) {
        lectureInfoDto.setLectureId(lecture.getId());
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
