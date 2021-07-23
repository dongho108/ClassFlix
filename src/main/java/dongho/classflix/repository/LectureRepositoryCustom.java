package dongho.classflix.repository;

import dongho.classflix.controller.dto.HomeLectureDto;
import dongho.classflix.controller.dto.LectureSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LectureRepositoryCustom {
    Page<HomeLectureDto> searchPageSort(LectureSearchCondition condition, Pageable pageable);
}
