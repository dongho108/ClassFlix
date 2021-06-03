package dongho.classflix.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.swing.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Review extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;

    private Integer rating;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    private LocalDateTime reviewDate;

    protected Review() {
    }

    public Review(Member member, String content, Integer rating, Lecture lecture, LocalDateTime reviewDate) {
        this.member = member;
        this.content = content;
        this.rating = rating;
        this.lecture = lecture;
        this.reviewDate = reviewDate;
    }

    public void changeContentAndRating(String content, Integer rating) {
        this.content = content;
        this.rating = rating;
    }
}
