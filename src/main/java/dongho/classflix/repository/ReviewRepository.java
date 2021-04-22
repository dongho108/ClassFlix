package dongho.classflix.repository;

import dongho.classflix.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {
    private final EntityManager em;

    // 리뷰 저장
    public Long save(Review review) {
        em.persist(review);
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

    // 멤버가 강의에 단 리뷰
    public List<Review> findAllWithMemberLecture() {
        return em.createQuery(
                "select r from Review r" +
                        " join fetch r.member m" +
                        " join fetch r.lecture l", Review.class
        ).getResultList();
    }
}
