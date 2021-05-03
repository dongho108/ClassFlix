package dongho.classflix.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeLectureDto {
    private Long id;
    private byte[] representImage;
    private String lectureName;
    private double averageRating;
}
