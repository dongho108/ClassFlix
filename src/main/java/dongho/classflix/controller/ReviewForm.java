package dongho.classflix.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ReviewForm {
    @NotEmpty(message = "회원 이름을 선택해주세요")
    private String memberName;

    private Integer rating;
    private String content;
}
