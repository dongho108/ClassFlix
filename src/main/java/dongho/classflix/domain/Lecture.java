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

    private byte[] RepresentImage;

    private String teacherName;
    private String siteName;
    private URI uri;
    private String content;

    @OneToMany(mappedBy = "lecture", cascade = ALL)
    private List<Review> reviews = new ArrayList<>();

}
