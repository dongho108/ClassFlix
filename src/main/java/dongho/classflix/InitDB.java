package dongho.classflix;

import dongho.classflix.domain.Gender;
import dongho.classflix.domain.Lecture;
import dongho.classflix.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@Profile("local")
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

            Lecture lecture1 = new Lecture("스프링입문", "김영한", "좋아요", "인프런", uri);
            Lecture lecture2 = new Lecture("스프링코어", "김영한", "나빠요", "클래스101", uri);
            Lecture lecture3 = new Lecture("jpa기초", "김영한", "그냥그래요", "패스트캠퍼스", uri);
            Lecture lecture4 = new Lecture("jpa활용", "김영한", "좋아요", "Class Flix", uri);
            em.persist(lecture1);
            em.persist(lecture2);
            em.persist(lecture3);
            em.persist(lecture4);

            for (int i = 0; i < 100; i++) {
                em.persist(new Lecture("강의"+i, "김영한", "샘플데이터", "사이트"+i, uri));
            }


            Member member = new Member("김동호", 25, Gender.FEMALE, "학생");
            em.persist(member);

        }
    }
}
