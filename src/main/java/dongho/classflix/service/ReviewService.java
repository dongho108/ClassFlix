package dongho.classflix.service;

import dongho.classflix.domain.Review;
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
        reviewRepository.saveWithLecture(review);
        return review.getId();
    }

    // 하나 조회
    @Transactional(readOnly = true)
    public Review findById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow();
    }


    // 전체 조회
    @Transactional(readOnly = true)
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    // 강의에 달린 리뷰 조회
    @Transactional(readOnly = true)
    public List<Review> findByLecture(Long lectureId) {
        return reviewRepository.findAllByLecture_Id(lectureId);
    }

    // 리뷰 수정
    public Long update(Long reviewId, Long lectureId, String content, Integer rating) {
        Review findReview = reviewRepository.findById(reviewId).orElseThrow();
        findReview.changeContentAndRating(content, rating);
        lectureService.refreshAverageRating(lectureId);
        return reviewId;
    }

    // 리뷰 삭제
    public Long delete(Long reviewId, Long lectureId) {
        lectureService.deleteReview(lectureId, reviewId);
        reviewRepository.delete(reviewRepository.findById(reviewId).orElseThrow());
        return reviewId;
    }

}
