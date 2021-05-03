package dongho.classflix.controller;

import dongho.classflix.domain.Lecture;
import dongho.classflix.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewForm {
    private Member member;
    private String password;
    private Integer rating;
    private Lecture lecture;
}
