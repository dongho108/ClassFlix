package dongho.classflix.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.time.LocalDateTime;

@Getter
@Setter
public class LectureForm {
    @NotEmpty(message = "강의 제목은 필수 입니다")
    private String lectureName;

    @NotEmpty(message = "강의자 이름은 필수 입니다")
    private String teacherName;

    private String content;
    private byte[] representImage;
    private String siteName;
    private URI uri;
}
