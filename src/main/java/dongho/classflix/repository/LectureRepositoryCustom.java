package dongho.classflix.repository;

import dongho.classflix.domain.Lecture;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LectureRepositoryCustom {
    List<Lecture> findAllPageSort(Pageable pageable);
}
