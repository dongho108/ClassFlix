package dongho.classflix.repository;

import dongho.classflix.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long>, LectureRepositoryCustom {
    List<Lecture> findByLectureNameAndTeacherName(String lectureName, String teacherName);
}
