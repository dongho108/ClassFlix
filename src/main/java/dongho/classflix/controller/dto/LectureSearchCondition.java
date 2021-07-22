package dongho.classflix.controller.dto;

import lombok.Data;

@Data
public class LectureSearchCondition {

    // 강의명, 강의자명, 별점(Goe)

    private String lectureName;
    private String teacherName;
    private Integer rating; // 실제는 double임. 안되면 수정필요

}
