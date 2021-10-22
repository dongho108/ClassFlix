package dongho.classflix.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.net.URI;

@Getter
@Setter
public class LectureForm {
    @NotBlank(message = "강의 제목을 입력해 주세요.")
    private String lectureName;

    @NotBlank(message = "강의자 이름을 입력해 주세요.")
    private String teacherName;

    private String content;
    private MultipartFile image;

    @NotBlank(message = "강의가 올려져 있는 사이트를 입력해 주세요.")
    private String siteName;

//    @URL(message = "URL을 제대로 입력했는지 확인해 주세요.")
//    private String uri;
    private URI uri;
}
