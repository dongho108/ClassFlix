package dongho.classflix.service;

import dongho.classflix.domain.Gender;
import dongho.classflix.domain.Lecture;
import dongho.classflix.domain.Member;
import dongho.classflix.domain.Review;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReviewServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    ReviewService reviewService;

    //리뷰 등록 수정

    @Test
    public void 리뷰등록() throws Exception {
        //given

        Member member = new Member("dongho", 25, Gender.MALE);
        em.persist(member);

        Lecture lecture = new Lecture("jpa", "김영한", "jpa강의");
        em.persist(lecture);

        //when
        Review review1 = new Review(member, "1234", "good", 4, lecture, LocalDateTime.now());
        reviewService.create(review1);

        Review review2 = new Review(member, "1234", "bad", 1, lecture, LocalDateTime.now());
        reviewService.create(review2);

        Review findReview1 = reviewService.findOne(review1.getId());
        Review findReview2 = reviewService.findOne(review1.getId());
        List<Review> reviews = reviewService.findAll();

        //then
        assertThat(findReview1).isEqualTo(review1);
        assertThat(findReview2).isEqualTo(review2);
        assertThat(reviews.size()).isEqualTo(2);
    }

    // 리뷰 수정
    @Test
    public void 리뷰수정() throws Exception {
        //given
        Member member = new Member("dongho", 25, Gender.MALE);
        em.persist(member);

        Lecture lecture = new Lecture("jpa", "김영한", "jpa강의");
        em.persist(lecture);

        //when
        Review review1 = new Review(member, "1234", "good", 4, lecture, LocalDateTime.now());
        reviewService.create(review1);

        Review review2 = new Review(member, "1234", "bad", 1, lecture, LocalDateTime.now());
        reviewService.create(review2);

        reviewService.update(review1.getId(), "1234", "very good", 5);
        assertThrows(NotEqualPasswordException.class, () -> {
            reviewService.update(review2.getId(), "1111", "so bad", 1);
        });

        //then
        assertThat(review1.getContent()).isEqualTo("very good");
        assertThat(review1.getRating()).isEqualTo(5);


    }


}