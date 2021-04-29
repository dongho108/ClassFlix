package dongho.classflix.service;

import dongho.classflix.domain.Lecture;
import dongho.classflix.repository.LectureRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LectureServiceTest {
    @Autowired
    EntityManager em;

    @Autowired
    LectureService lectureService;

    @Autowired
    LectureRepository lectureRepository;


    @Test
    public void 중복강의거부() throws Exception {
        //given
        Lecture lecture1 = new Lecture("jpa", "김영한", "jpa강의", LocalDateTime.now());
        Lecture lecture2 = new Lecture("jpa", "김영한", "jpa강의입니다.", LocalDateTime.now());

        //when
        lectureService.join(lecture1);

        //then
        assertThrows(IllegalStateException.class, () -> {
            lectureService.join(lecture2);
        });

    }
    // 강의 업데이트
    @Test
    public void 강의수정() throws Exception {
        //given
        Lecture lecture = new Lecture("jpa", "김영한", "jpa강의", LocalDateTime.now());
        em.persist(lecture);

        LectureDto lectureDto = new LectureDto();
        lectureDto.setContent("data jpa 강의");

        //when
        lectureService.update(lecture.getId(), lectureDto);

        //then
        assertThat(lecture.getContent()).isEqualTo("data jpa 강의");
    }

    @Test
    public void 강의조회() throws Exception {
        //given
        Lecture lecture1 = new Lecture("스프링입문", "김영한", "좋아요", LocalDateTime.now());
        Lecture lecture2 = new Lecture("스프링코어", "김영한", "나빠요", LocalDateTime.now());
        Lecture lecture3 = new Lecture("jpa기초", "김영한", "그냥그래요", LocalDateTime.now());
        Lecture lecture4 = new Lecture("jpa활용", "김영한", "좋아요", LocalDateTime.now());

        //when
        List<Lecture> lectures = lectureService.findAll();

        //then
        assertThat(lectures.get(0).getLectureName()).isEqualTo(lecture1.getLectureName());
        assertThat(lectures.get(1).getLectureName()).isEqualTo(lecture2.getLectureName());
        assertThat(lectures.get(2).getLectureName()).isEqualTo(lecture3.getLectureName());
        assertThat(lectures.get(3).getLectureName()).isEqualTo(lecture4.getLectureName());
    }

    @Test
    public void 강의사진저장조회() throws Exception {
        //given
        Lecture lecture1 = new Lecture("스프링입문", "김영한", "좋아요", LocalDateTime.now());

        //when

        //then
    }
}