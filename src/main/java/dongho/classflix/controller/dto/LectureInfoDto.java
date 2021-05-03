package dongho.classflix.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.net.URI;

@Setter
@Getter
public class LectureInfoDto {
    private String lectureName;
    private String teacherName;
    private String content;
    private String siteName;
    private String uri;
    private double averageRating;
    private int reviewNum;
}
