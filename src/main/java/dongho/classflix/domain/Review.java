package dongho.classflix.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.swing.*;
import java.time.LocalDateTime;
import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Review {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private JPasswordField password;

    private String content;

    @Enumerated(EnumType.STRING)
    private Star star;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    private LocalDateTime reviewDate;
}
