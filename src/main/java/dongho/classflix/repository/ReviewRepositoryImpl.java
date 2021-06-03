package dongho.classflix.repository;

import dongho.classflix.domain.Lecture;
import dongho.classflix.domain.Review;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private final EntityManager em;

    @Override
    public Review saveWithLecture(Review review) {
        em.persist(review);
        Lecture lecture = review.getLecture();
        lecture.addReview(review);
        return review;
    }
}
