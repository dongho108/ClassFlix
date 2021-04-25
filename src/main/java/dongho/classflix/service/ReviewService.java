package dongho.classflix.service;

import dongho.classflix.ReviewDto;
import dongho.classflix.domain.Lecture;
import dongho.classflix.domain.Member;
import dongho.classflix.domain.Review;
import dongho.classflix.repository.LectureRepository;
import dongho.classflix.repository.MemberRepository;
import dongho.classflix.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final LectureRepository lectureRepository;

    // 리뷰 등록
    public Long create(Long memberId, Long lectureId, ReviewDto reviewDto) {
        Member findMember = memberRepository.findById(memberId);
        Lecture findLecture = lectureRepository.findById(lectureId);

        Review newReview = new Review(findMember, reviewDto.getPassword(), reviewDto.getContent(), reviewDto.getRating(), findLecture, reviewDto.getReviewDate());
        reviewRepository.save(newReview);
        return newReview.getId();
    }

    // 하나 조회
    @Transactional(readOnly = true)
    public Review findOne(Long reviewId) {
        return reviewRepository.findById(reviewId);
    }


    // 전체 조회
    @Transactional(readOnly = true)
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    // 리뷰 수정
    public Long update(Long reviewId, String password, String content, Integer rating) {
        Review findReview = reviewRepository.findById(reviewId);

        if (!findReview.getPassword().equals(password)) {
            throw new NotEqualPasswordException("password is wrong");
        }
        findReview.changeContentAndRating(content, rating);
        return reviewId;
    }

}
