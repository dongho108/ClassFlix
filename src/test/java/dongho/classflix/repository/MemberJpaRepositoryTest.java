package dongho.classflix.repository;

import dongho.classflix.domain.Gender;
import dongho.classflix.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {


    @Autowired
    MemberJpaRepository memberRepository;

    @Test
    public void 회원저장조회() throws Exception {
        //given
        Member member = new Member("dongho", 25, Gender.MALE);
        memberRepository.save(member);

        //when
        Member nullMember = new Member("null", 0, null);
        Member savedMember = memberRepository.findById(member.getId()).orElse(nullMember);

        //then
        Assertions.assertThat(member).isEqualTo(savedMember);
    }
}