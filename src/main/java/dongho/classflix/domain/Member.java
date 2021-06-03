package dongho.classflix.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity{
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String userName;

    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String career;

    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();

    public Member(String userName, int age, Gender gender) {
        this.userName = userName;
        this.age = age;
        this.gender = gender;
    }

    public Member(String userName, int age, Gender gender, String career) {
        this.userName = userName;
        this.age = age;
        this.gender = gender;
        this.career = career;
    }
}
