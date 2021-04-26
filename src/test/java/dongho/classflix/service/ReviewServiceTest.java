package dongho.classflix.service;

import dongho.classflix.domain.Gender;
import dongho.classflix.domain.Lecture;
import dongho.classflix.domain.Member;
import dongho.classflix.domain.Review;
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

        Lecture lecture = new Lecture("jpa", "김영한", "jpa강의", LocalDateTime.now());
        em.persist(lecture);

        //when
        ReviewDto reviewDto1 = new ReviewDto("1234", "good", 4, LocalDateTime.now());
        Long reviewId1 = reviewService.create(member.getId(), lecture.getId(), reviewDto1);

        ReviewDto reviewDto2 = new ReviewDto("1234", "bad", 4, LocalDateTime.now());
        Long reviewId2 = reviewService.create(member.getId(), lecture.getId(), reviewDto2);

        Review findReview1 = reviewService.findOne(reviewId1);
        Review findReview2 = reviewService.findOne(reviewId2);
        List<Review> reviews = reviewService.findAll();

        //then
        assertThat(findReview1.getId()).isEqualTo(reviewId1);
        assertThat(findReview2.getId()).isEqualTo(reviewId2);
        assertThat(reviews.size()).isEqualTo(2);
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
        ReviewDto reviewDto1 = new ReviewDto("1234", "good", 4, LocalDateTime.now());
        Long reviewId1 = reviewService.create(member.getId(), lecture.getId(), reviewDto1);

        ReviewDto reviewDto2 = new ReviewDto("1234", "bad", 4, LocalDateTime.now());
        Long reviewId2 = reviewService.create(member.getId(), lecture.getId(), reviewDto2);

        reviewService.update(reviewId1, "1234", "very good", 5);
        assertThrows(NotEqualPasswordException.class, () -> {
            reviewService.update(reviewId2, "1111", "so bad", 1);
        });

        Review review1 = reviewService.findOne(reviewId1);

        //then
        assertThat(review1.getContent()).isEqualTo("very good");
        assertThat(review1.getRating()).isEqualTo(5);

    }


}