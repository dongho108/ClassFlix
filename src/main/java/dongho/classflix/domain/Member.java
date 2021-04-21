package dongho.classflix.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String career;
}
