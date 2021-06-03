package dongho.classflix.service;

import dongho.classflix.domain.*;
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
class ReviewJpaServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    ReviewService reviewService;

    @Autowired
    LectureService lectureService;

    //리뷰 등록 수정

    @Test
    public void 리뷰등록() throws Exception {

        //given

        Member member = new Member("dongho", 25, Gender.MALE);
        em.persist(member);

        Lecture lecture = new Lecture("jpa", "김영한", "jpa강의", LocalDateTime.now());
        em.persist(lecture);

        //when
        Review review1 = new Review(member,"good", 4, lecture, LocalDateTime.now());
        Long reviewId1 = reviewService.create(review1);

        Review review2 = new Review(member, "good", 4, lecture, LocalDateTime.now());
        Long reviewId2 = reviewService.create(review2);

        Review findReview1 = reviewService.findById(reviewId1);
        Review findReview2 = reviewService.findById(reviewId2);
        List<Review> reviews = reviewService.findAll();



        //then
        assertThat(findReview1.getId()).isEqualTo(reviewId1);
        assertThat(findReview2.getId()).isEqualTo(reviewId2);
        assertThat(reviews.size()).isEqualTo(2);
        assertThat(lecture.getReviewNum()).isEqualTo(2);
    }

    // 리뷰 수정
    @Test
    public void 리뷰수정() throws Exception {
        //given
        Member member = new Member("dongho", 25, Gender.MALE);
        em.persist(member);

        Lecture lecture = new Lecture("jpa", "김영한", "jpa강의", LocalDateTime.now());
        em.persist(lecture);

        //when
        Review review1 = new Review(member,"good", 3, lecture, LocalDateTime.now());
        Long reviewId1 = reviewService.create(review1);

        Review review2 = new Review(member,"good", 1, lecture, LocalDateTime.now());
        reviewService.create(review2);

        reviewService.update(reviewId1, lecture.getId(), "very good", 5);

        //then
        assertThat(review1.getContent()).isEqualTo("very good");
        assertThat(review1.getRating()).isEqualTo(5);
        assertThat(lecture.getAverageRating()).isEqualTo(3);

    }

    //리뷰삭제
    @Test
    public void 리뷰삭제() throws Exception {
        //given

        Member member = new Member("dongho", 25, Gender.MALE);
        em.persist(member);

        Lecture lecture = new Lecture("jpa", "김영한", "jpa강의", LocalDateTime.now());
        em.persist(lecture);

        //when
        Review review1 = new Review(member,"bad", 2, lecture, LocalDateTime.now());
        Long reviewId1 = reviewService.create(review1);

        Review review2 = new Review(member,"good", 5, lecture, LocalDateTime.now());
        Long reviewId2 = reviewService.create(review2);


        //then
        reviewService.delete(reviewId2, lecture.getId());
        assertThat(lecture.getReviewNum()).isEqualTo(1);
        assertThat(lecture.getAverageRating()).isEqualTo(2);

        reviewService.delete(reviewId1, lecture.getId());
        assertThat(lecture.getReviewNum()).isEqualTo(0);
        assertThat(lecture.getAverageRating()).isEqualTo(0);
        assertThrows(NotEnoughReviewException.class, () -> {
            reviewService.delete(reviewId1, lecture.getId());
        });
    }

    @Test
    public void 강의에달린리뷰조회() throws Exception {
        //given

        Member member = new Member("dongho", 25, Gender.MALE);
        em.persist(member);

        Lecture lecture = new Lecture("jpa", "김영한", "jpa강의", LocalDateTime.now());
        em.persist(lecture);

        //when
        Review review1 = new Review(member,"bad", 2, lecture, LocalDateTime.now());
        reviewService.create(review1);

        Review review2 = new Review(member,"good", 5, lecture, LocalDateTime.now());
        reviewService.create(review2);

        //then

        List<Review> lectures = reviewService.findByLecture(lecture.getId());
        assertThat(lectures.size()).isEqualTo(2);
    }
}