package dongho.classflix.service;

import dongho.classflix.domain.Gender;
import dongho.classflix.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberService memberService;


    @Test
    public void 중복회원예외() throws Exception {
        //given
        Member member1 = new Member("dongho", 25, Gender.MALE);
        Member member2 = new Member("dongho", 28, Gender.MALE);

        //when
        memberService.join(member1);

        //then
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
    }
}