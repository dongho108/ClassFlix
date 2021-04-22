package dongho.classflix.domain;

import lombok.Getter;

import javax.persistence.*;
import java.awt.*;
import java.net.URI;
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


    @OneToMany(mappedBy = "lecture", cascade = ALL)
    private List<Review> reviews = new ArrayList<>();

    protected Lecture() {
    }

    public Lecture(String lectureName, String teacherName, String content) {
        this.lectureName = lectureName;
        this.teacherName = teacherName;
        this.content = content;
    }
}
