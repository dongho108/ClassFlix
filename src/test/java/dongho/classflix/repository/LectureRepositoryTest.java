package dongho.classflix.repository;

import dongho.classflix.domain.Lecture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LectureRepositoryTest {

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    EntityManager em;

    @Test
    public void 강의저장조회() throws Exception {
        //given
        Lecture lecture = new Lecture("jpa", "김영한", "jpa강의");

        //when
        Long savedId = lectureRepository.save(lecture);

        //then
        Assertions.assertThat(lecture).isEqualTo(lectureRepository.findById(savedId));
    }
}