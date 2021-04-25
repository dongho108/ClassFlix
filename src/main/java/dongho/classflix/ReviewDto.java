package dongho.classflix;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewDto {
    //    ,password,content,rating,reviewDate
    private String password;
    private String content;
    private int rating;
    private LocalDateTime reviewDate;
}
