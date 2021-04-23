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

    // 리뷰 등록
    public Long create(Review review) {
        reviewRepository.save(review);
        return review.getId();
    }

    // 하나 조회
    public Review findOne(Long reviewId) {
        return reviewRepository.findById(reviewId);
    }


    // 전체 조회
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
