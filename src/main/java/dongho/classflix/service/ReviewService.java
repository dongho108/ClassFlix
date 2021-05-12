package dongho.classflix.service;

import dongho.classflix.domain.Lecture;
import dongho.classflix.domain.Member;
import dongho.classflix.domain.Review;
import dongho.classflix.repository.LectureRepository;
import dongho.classflix.repository.MemberRepository;
import dongho.classflix.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final LectureService lectureService;

    // 리뷰 등록
    public Long create(Review review) {
        reviewRepository.save(review);
        return review.getId();
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

    @Transactional(readOnly = true)
    public List<Review> findByLecture(Long lectureId) {
        return reviewRepository.findAllWithLecture(lectureId);
    }

    // 리뷰 수정
    public Long update(Long reviewId, Long lectureId, String content, Integer rating) {
        Review findReview = reviewRepository.findById(reviewId);
        lectureService.refreshAverageRating(lectureId, findReview.getRating(), rating);
        findReview.changeContentAndRating(content, rating);
        return reviewId;
    }

}
