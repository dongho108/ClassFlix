package dongho.classflix.service;

import lombok.Data;

import java.net.URI;

@Data
public class LectureDto {
    private String lectureName;
    private String teacherName;
    private String content;
    private byte[] representImage;
    private String siteName;
    private URI uri;
}
