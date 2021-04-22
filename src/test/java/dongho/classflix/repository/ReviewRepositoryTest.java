package dongho.classflix.repository;

import dongho.classflix.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.swing.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ReviewRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LectureRepository lectureRepository;


    @Test
    public void 리뷰저장조회() throws Exception {
        //given
        Member member = new Member("dongho", 25, Gender.MALE);
        em.persist(member);

        Lecture lecture = new Lecture("jpa", "김영한", "jpa강의");
        em.persist(lecture);

        Review review = new Review(member, "1234", "good", Star.FOUR, lecture, LocalDateTime.now());
        //when

        Long savedId = reviewRepository.save(review);

        //then
        Assertions.assertThat(review).isEqualTo(reviewRepository.findById(savedId));
    }
}