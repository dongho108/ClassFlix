package dongho.classflix;

import dongho.classflix.domain.Gender;
import dongho.classflix.domain.Lecture;
import dongho.classflix.domain.Member;
import dongho.classflix.domain.Review;
import dongho.classflix.service.ReviewDto;
import dongho.classflix.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() throws URISyntaxException {
        initService.dbInit1();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() throws URISyntaxException {
            URI uri = new URI("https://www.inflearn.com/");

            Lecture lecture1 = new Lecture("스프링입문", "김영한", "좋아요", LocalDateTime.now(), 5, "인프런", uri);
            Lecture lecture2 = new Lecture("스프링코어", "김영한", "나빠요", LocalDateTime.now(), 4, "클래스101", uri);
            Lecture lecture3 = new Lecture("jpa기초", "김영한", "그냥그래요", LocalDateTime.now(), 3, "패스트캠퍼스", uri);
            Lecture lecture4 = new Lecture("jpa활용", "김영한", "좋아요", LocalDateTime.now(), 2, "CLass Flix", uri);

            em.persist(lecture1);
            em.persist(lecture2);
            em.persist(lecture3);
            em.persist(lecture4);

        }
    }
}
