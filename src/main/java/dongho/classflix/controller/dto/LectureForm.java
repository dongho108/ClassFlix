package dongho.classflix.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.time.LocalDateTime;

@Getter
@Setter
public class LectureForm {
    private String lectureName;
    private String teacherName;
    private String content;
    private byte[] representImage;
    private String siteName;
    private URI uri;
}
