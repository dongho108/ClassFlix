package dongho.classflix.controller.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewDto {
    //    ,password,content,rating,reviewDate
    private Long reviewId;
    private String memberName;
    private String password;
    private String content;
    private int rating;
}
