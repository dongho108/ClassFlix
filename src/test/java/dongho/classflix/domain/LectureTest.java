package dongho.classflix.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LectureTest {

    @Autowired
    EntityManager em;

    // 리뷰 개수와 평균별점
    @Test
    public void 강의의리뷰개수추가() throws Exception {
        //given
        Member member = new Member("dongho", 25, Gender.MALE);
        em.persist(member);

        Lecture lecture = new Lecture("jpa", "김영한", "jpa강의", LocalDateTime.now());
        em.persist(lecture);

        Review review1 = new Review(member, "1234", "good", 4, lecture, LocalDateTime.now());
        em.persist(review1);

        Review review2 = new Review(member, "1234", "bad", 1, lecture, LocalDateTime.now());
        em.persist(review2);


        //when
        lecture.addReview(review1.getRating());
        lecture.addReview(review2.getRating());

        //then
        Assertions.assertThat(lecture.getReviewNum()).isEqualTo(2);

    }

    @Test
    public void 강의의리뷰개수감소() throws Exception {
        //given
        Member member = new Member("dongho", 25, Gender.MALE);
        em.persist(member);

        Lecture lecture = new Lecture("jpa", "김영한", "jpa강의", LocalDateTime.now());
        em.persist(lecture);

        Review review1 = new Review(member, "1234", "good", 4, lecture, LocalDateTime.now());
        em.persist(review1);

        Review review2 = new Review(member, "1234", "bad", 1, lecture, LocalDateTime.now());
        em.persist(review2);


        //when
        lecture.addReview(review1.getRating());
        lecture.addReview(review2.getRating());
        lecture.removeReview(review1.getRating());
        lecture.removeReview(review2.getRating());

        //then
        Assertions.assertThat(lecture.getReviewNum()).isEqualTo(0);

        assertThrows(NotEnoughReviewException.class, () -> {
            lecture.removeReview(1);
        });

    }

    @Test
    public void 강의평균별점() throws Exception {
        //given
        Member member = new Member("dongho", 25, Gender.MALE);
        em.persist(member);

        Lecture lecture = new Lecture("jpa", "김영한", "jpa강의", LocalDateTime.now());
        em.persist(lecture);

        Review review1 = new Review(member, "1234", "good", 4, lecture, LocalDateTime.now());
        em.persist(review1);

        Review review2 = new Review(member, "1234", "bad", 1, lecture, LocalDateTime.now());
        em.persist(review2);


        //when
        lecture.addReview(review1.getRating());
        lecture.addReview(review2.getRating());

        //then
        Assertions.assertThat(lecture.getAverageRating()).isEqualTo(2);
    }


}