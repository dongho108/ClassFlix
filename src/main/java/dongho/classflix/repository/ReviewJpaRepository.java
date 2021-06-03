package dongho.classflix.repository;

import dongho.classflix.domain.Lecture;
import dongho.classflix.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewJpaRepository {
    private final EntityManager em;

    // 리뷰 저장
    public Long save(Review review) {
        em.persist(review);
        Lecture lecture = review.getLecture();
        lecture.addReview(review);
        return review.getId();
    }

    // 리뷰조회
    public Review findById(Long id) {
        return em.find(Review.class, id);
    }

    public List<Review> findAll() {
        return em.createQuery("select r from Review r", Review.class)
                .getResultList();
    }

    // 강의에 달린 리뷰
    public List<Review> findAllWithLecture(Long lectureId) {
        return em.createQuery(
                "select r from Review r" +
                        " join fetch r.lecture l" +
                        " where l.id = :lectureId", Review.class)
                .setParameter("lectureId", lectureId)
                .getResultList();
    }

    public Long delete(Long reviewId) {
        Review findReview = findById(reviewId);
        em.remove(findReview);
        return reviewId;
    }
}
