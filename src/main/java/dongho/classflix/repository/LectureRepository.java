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

    // 강의 저장
    public Long save(Lecture lecture) {
        em.persist(lecture);
        return lecture.getId();
    }

    // 강의 조회
    public Lecture findById(Long id) {
        return em.find(Lecture.class, id);
    }

    public List<Lecture> findAll() {
        return em.createQuery("select l from Lecture l", Lecture.class)
                .getResultList();
    }


}
