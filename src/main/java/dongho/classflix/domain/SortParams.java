package dongho.classflix.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * code는 검색파라미터 역할
 * displayName은 고객에게 보여주는 값
 *
 * createdDate,DESC : 최신순
 * lectureName,ASC : 이름순
 */

@Data
@AllArgsConstructor
public class SortParams {
    private String code;
    private String displayName;
}
