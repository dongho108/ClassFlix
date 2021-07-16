package dongho.classflix.repository;

import dongho.classflix.controller.dto.HomeLectureDto;
import dongho.classflix.controller.dto.PageDto;
import dongho.classflix.domain.Lecture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LectureRepositoryCustom {
    Page<HomeLectureDto> findAllPageSort(Pageable pageable);
}
