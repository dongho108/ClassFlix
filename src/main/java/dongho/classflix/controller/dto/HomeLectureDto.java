package dongho.classflix.controller.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HomeLectureDto {
    private Long id;
    private String representImagePath;
//    private String representImageName;
    private String lectureName;
    private double averageRating;

    @QueryProjection
    public HomeLectureDto(Long id, String representImagePath, String lectureName, double averageRating) {
        this.id = id;
        this.representImagePath = representImagePath;
        this.lectureName = lectureName;
        this.averageRating = averageRating;
    }
}
