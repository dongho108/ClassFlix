package dongho.classflix.repository;

import dongho.classflix.domain.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LectureRepository {

    private final EntityManager em;


    // 강의 등록
    public Long save(Lecture lecture) {
        em.persist(lecture);
        return lecture.getId();
    }


    // 강의 조회
    public Lecture findById(Long id) {
        return em.find(Lecture.class, id);
    }

    public List<Lecture> findAll() {
        List<Lecture> lectures = em.createQuery("select l from Lecture l", Lecture.class)
                .getResultList();
        if (lectures.isEmpty()) {
            throw new NullPointerException("조회 결과가 없습니다");
        }
        return lectures;
    }

    public List<Lecture> findByName(String lectureName, String teacherName) {
        return em.createQuery("select l from Lecture l " +
                "where l.lectureName = :lectureName and l.teacherName = :teacherName", Lecture.class)
                .setParameter("lectureName", lectureName)
                .setParameter("teacherName", teacherName)
                .getResultList();
    }


}
