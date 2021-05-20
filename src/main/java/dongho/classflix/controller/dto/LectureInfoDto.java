package dongho.classflix.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LectureInfoDto {
    private Long lectureId;
    private String lectureName;
    private String teacherName;
    private String content;
    private String siteName;
    private String uri;
    private String imagePath;
    private int averageRating;
    private int reviewNum;
}
