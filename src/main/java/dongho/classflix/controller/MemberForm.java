package dongho.classflix.controller;

import dongho.classflix.domain.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MemberForm {

    @NotBlank(message = "회원 이름을 입력해 주세요.")
    private String name;

    @NotNull(message = "나이를 입력해 주세요.")
    private Integer age;

    @NotNull(message = "성별을 선택해 주세요.")
    private Gender gender;

    @NotBlank(message = "직업을 입력해 주세요.")
    private String career;
}
