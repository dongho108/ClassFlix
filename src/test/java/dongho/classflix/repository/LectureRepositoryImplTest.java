package dongho.classflix.repository;

import dongho.classflix.controller.dto.HomeLectureDto;
import dongho.classflix.controller.dto.LectureSearchCondition;
import dongho.classflix.domain.Lecture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Lecture lecture3 = new Lecture("jpa기초", "김동호", "그냥그래요");
        Lecture lecture4 = new Lecture("jpa활용", "김동호", "좋아요");
        em.persist(lecture1);
        em.persist(lecture2);
        em.persist(lecture3);
        em.persist(lecture4);

        //when
        LectureSearchCondition condition = new LectureSearchCondition();
        PageRequest createdDate = PageRequest.of(1, 2, Sort.Direction.DESC, "createdDate");

        Page<HomeLectureDto> results = lectureRepository.findAllPageSort(condition, createdDate);

        //then
        List<HomeLectureDto> content = results.getContent();
        assertAll("page : 1, size : 2, sort : createDate&DESC",
                () -> assertEquals(content.get(0).getId(), lecture2.getId()),
                () -> assertEquals(content.get(1).getId(), lecture1.getId()));


        for (HomeLectureDto lecture : content) {
            System.out.println("lecture = " + lecture.getLectureName());
        }
    }

    @Test
    public void searchTest() throws Exception {
        //given
        Lecture lecture1 = new Lecture("스프링입문", "김영한", "좋아요");
        Lecture lecture2 = new Lecture("스프링입문", "김영한", "나빠요");
        Lecture lecture3 = new Lecture("jpa기초", "김동호", "그냥그래요");
        Lecture lecture4 = new Lecture("jpa활용", "김동호", "좋아요");
        em.persist(lecture1);
        em.persist(lecture2);
        em.persist(lecture3);
        em.persist(lecture4);

        //when
        LectureSearchCondition condition = new LectureSearchCondition();
        condition.setLectureName("스프링입문");
        PageRequest createdDate = PageRequest.of(0, 2, Sort.Direction.DESC, "createdDate");


        //then
        Page<HomeLectureDto> results = lectureRepository.findAllPageSort(condition, createdDate);
        List<HomeLectureDto> content = results.getContent();
        assertAll("page : 0, size : 2, sort : createDate&DESC" +
                        "search : lectureName(스프링입문)",
                () -> assertEquals(content.get(0).getId(), lecture2.getId()),
                () -> assertEquals(content.get(1).getId(), lecture1.getId()));
    }

}