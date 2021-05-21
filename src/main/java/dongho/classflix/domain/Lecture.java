package dongho.classflix.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class Lecture {

    @Id
    @GeneratedValue
    @Column(name = "lecture_id")
    private Long id;

    private String lectureName;

    private String teacherName;

    private String content;

    private String representImagePath;
    private long representImageSize;
    private String representImageName;

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


    // contoller에서 실제로 사용할 생성자
    public Lecture(String lectureName, String teacherName, String content, String siteName, URI uri, LocalDateTime lectureDateTime) {
        this.lectureName = lectureName;
        this.teacherName = teacherName;
        this.content = content;
        this.siteName = siteName;
        this.uri = uri;
        this.lectureDateTime = lectureDateTime;
    }

    public Lecture(String lectureName, String teacherName, String content, String representImagePath, long representImageSize, String representImageName, String siteName, URI uri, LocalDateTime lectureDateTime) {
        this.lectureName = lectureName;
        this.teacherName = teacherName;
        this.content = content;
        this.representImagePath = representImagePath;
        this.representImageSize = representImageSize;
        this.representImageName = representImageName;
        this.siteName = siteName;
        this.uri = uri;
        this.lectureDateTime = lectureDateTime;
    }

    // 테스트 데이터용
    public Lecture(String lectureName, String teacherName, String content, LocalDateTime lectureDateTime, String siteName, URI uri) {
        this.lectureName = lectureName;
        this.teacherName = teacherName;
        this.content = content;
        this.lectureDateTime = lectureDateTime;
        this.siteName = siteName;
        this.uri = uri;
    }

    public void addReview(Review review) {
        this.reviewNum += 1;
        reviews.add(review);
        if (this.reviewNum < 100) {
            calculateAverageRating();
        }
        else {
            addAverageRating(review.getRating());
        }

    }

    public void removeReview(Long reviewId) {
        int restReview = this.reviewNum - 1;
        if (restReview < 0) {
            throw new NotEnoughReviewException("review is empty");
        }
        this.reviewNum -= 1;

        Review review = new Review();

        for (int i = 0; i < reviews.size(); i++) {
            review = reviews.get(i);
            if (review.getId().equals(reviewId)) {
                reviews.remove(i);
                break;
            }
        }

        if (reviewNum < 100) {
            calculateAverageRating();
        } else {
            subAverageRating(review.getRating());
        }

    }

    public void addAverageRating(Integer rating) {
        log.info("reviewNum = {}", reviewNum);
        if (reviewNum == 0) {
            this.averageRating = 0;
        } else {
            if (reviewNum == 1) {
                this.averageRating = rating;
            } else {
                double average = (averageRating + rating) / 2;
                log.info("averageRating = {}, rating = {}", averageRating, rating);
                this.averageRating = Math.floor(average);
            }
        }
    }

    public void subAverageRating(int rating) {
        if (reviewNum == 0) {
            this.averageRating = 0;
        } else {
            this.averageRating = Math.floor(((averageRating * (reviewNum + 1)) - rating) / reviewNum);
        }
    }

    public void calculateAverageRating() {
        double sum = 0;
        if (reviewNum == 0) {
            this.averageRating = 0;
            return;
        }
        for (int i = 0; i < reviewNum; i++) {
            sum += reviews.get(i).getRating();
        }
        this.averageRating = Math.floor(sum / reviewNum);
    }


    public void changeLectureData(String lectureName, String teacherName, String content, String representImagePath, long representImageSize, String representImageName, String siteName, URI uri) {
        this.lectureName = lectureName;
        this.teacherName = teacherName;
        this.content = content;
        this.representImagePath = representImagePath;
        this.representImageSize = representImageSize;
        this.representImageName = representImageName;
        this.siteName = siteName;
        this.uri = uri;
    }
}
