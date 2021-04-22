package dongho.classflix.repository;

import dongho.classflix.domain.Gender;
import dongho.classflix.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    public void 회원등록() throws Exception {
        //given
        Member member = new Member("dongho", 25, Gender.MALE);

        //when
        Long savedId = memberRepository.save(member);

        //then
        Assertions.assertThat(member).isEqualTo(memberRepository.findById(savedId));
    }
}