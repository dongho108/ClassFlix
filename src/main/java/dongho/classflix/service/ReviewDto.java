package dongho.classflix.service;


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

    public ReviewDto(String password, String content, int rating, LocalDateTime reviewDate) {
        this.password = password;
        this.content = content;
        this.rating = rating;
        this.reviewDate = reviewDate;
    }
}
