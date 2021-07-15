package dongho.classflix.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import dongho.classflix.domain.Lecture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LectureRepositoryImplTest {

    @Autowired
    EntityManager em;

    @Autowired
    LectureRepository lectureRepository;


    @Test
    public void findAllPageSortTest() throws Exception {
        //given
        Lecture lecture1 = new Lecture("스프링입문", "김영한", "좋아요");
        Lecture lecture2 = new Lecture("스프링코어", "김영한", "나빠요");
        Lecture lecture3 = new Lecture("jpa기초", "김영한", "그냥그래요");
        Lecture lecture4 = new Lecture("jpa활용", "김영한", "좋아요");
        em.persist(lecture1);
        em.persist(lecture2);
        em.persist(lecture3);
        em.persist(lecture4);

        //when
        List<Lecture> results = lectureRepository.findAllPageSort(PageRequest.of(1, 2, Sort.Direction.DESC, "createdDate"));

        //then

        assertAll("page : 1, size : 2, sort : createDate&DESC",
                () -> assertEquals(results.get(0).getId(), lecture2.getId()),
                () -> assertEquals(results.get(1).getId(), lecture1.getId()));


        for (Lecture lecture : results) {
            System.out.println("lecture = " + lecture.getLectureName() + " time : " + lecture.getCreatedDate());
        }
    }

}