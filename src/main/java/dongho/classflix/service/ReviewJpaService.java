package dongho.classflix.service;

import dongho.classflix.domain.Review;
import dongho.classflix.repository.ReviewJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewJpaService {

    private final ReviewJpaRepository reviewJpaRepository;
    private final LectureService lectureService;

    // 리뷰 등록
    public Long create(Review review) {
        reviewJpaRepository.save(review);
        return review.getId();
    }

    // 하나 조회
    @Transactional(readOnly = true)
    public Review findOne(Long reviewId) {
        return reviewJpaRepository.findById(reviewId);
    }


    // 전체 조회
    @Transactional(readOnly = true)
    public List<Review> findAll() {
        return reviewJpaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Review> findByLecture(Long lectureId) {
        return reviewJpaRepository.findAllWithLecture(lectureId);
    }

    // 리뷰 수정
    public Long update(Long reviewId, Long lectureId, String content, Integer rating) {
        Review findReview = reviewJpaRepository.findById(reviewId);
        findReview.changeContentAndRating(content, rating);
        lectureService.refreshAverageRating(lectureId);
        return reviewId;
    }

    // 리뷰 삭제
    public Long delete(Long reviewId, Long lectureId) {
        lectureService.deleteReview(lectureId, reviewId);
        reviewJpaRepository.delete(reviewId);
        return reviewId;
    }

}
