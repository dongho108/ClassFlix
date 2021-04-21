package dongho.classflix.domain;

import lombok.Getter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {
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

}
