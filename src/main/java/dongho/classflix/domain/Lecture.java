package dongho.classflix.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.awt.*;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
public class Lecture {

    @Id
    @GeneratedValue
    @Column(name = "lecture_id")
    private Long id;

    private String lectureName;

    private String teacherName;

    private String content;

    private byte[] representImage;
    private String siteName;
    private URI uri;
    private LocalDateTime lectureDateTime;

    private double averageRating;
    private int reviewNum;

    @OneToMany(mappedBy = "lecture", cascade = ALL)
    private List<Review> reviews = new ArrayList<>();

    protected Lecture() {
    }

    public Lecture(String lectureName, String teacherName, String content, LocalDateTime lectureDateTime) {
        this.lectureName = lectureName;
        this.teacherName = teacherName;
        this.content = content;
        this.lectureDateTime = lectureDateTime;
    }

    public Lecture(String lectureName, String teacherName, String content, LocalDateTime lectureDateTime, double averageRating) {
        this.lectureName = lectureName;
        this.teacherName = teacherName;
        this.content = content;
        this.lectureDateTime = lectureDateTime;
        this.averageRating = averageRating;
    }

    public Lecture(String lectureName, String teacherName, String content, byte[] representImage, String siteName, URI uri) {
        this.lectureName = lectureName;
        this.teacherName = teacherName;
        this.content = content;
        this.representImage = representImage;
        this.siteName = siteName;
        this.uri = uri;
    }

    public void addReview(Integer rating) {
        this.reviewNum += 1;
        updateAverageRating(rating);
    }

    public void removeReview(Integer rating) {
        int restReview = this.reviewNum - 1;
        if (restReview < 0) {
            throw new NotEnoughReviewException("review is empty");
        }
        this.reviewNum -= 1;
        updateAverageRating(rating);
    }

    public void updateAverageRating(Integer rating) {
        if (reviewNum == 0) {
            this.averageRating = 0;
        } else {
            double average = (averageRating + rating) / reviewNum;
            this.averageRating = Math.floor(average);
        }
    }

    public void changeLectureData(String lectureName, String teacherName, String content, byte[] representImage, String siteName, URI uri) {
        this.lectureName = lectureName;
        this.teacherName = teacherName;
        this.content = content;
        this.representImage = representImage;
        this.siteName = siteName;
        this.uri = uri;
    }

}
