package dongho.classflix.repository;

import dongho.classflix.domain.Gender;
import dongho.classflix.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    public void 회원저장조회() throws Exception {
        //given
        Member member = new Member("dongho", 25, Gender.MALE);
        memberRepository.save(member);
        em.flush();
        em.clear();

        //when
        Member savedMember = memberRepository.findById(member.getId()).orElseThrow();

        //then
        Assertions.assertThat(member.getId()).isEqualTo(savedMember.getId());
    }
}