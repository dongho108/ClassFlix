package dongho.classflix.repository;

import dongho.classflix.domain.Gender;
import dongho.classflix.domain.Lecture;
import dongho.classflix.domain.Member;
import dongho.classflix.domain.Review;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
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

        Review review = new Review(member, "good", 4, lecture);
        //when

        reviewRepository.save(review);
        Review findReview = reviewRepository.findById(review.getId()).orElseThrow();

        //then
        Assertions.assertThat(review.getId()).isEqualTo(findReview.getId());
    }
}