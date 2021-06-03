package dongho.classflix.service;

import dongho.classflix.domain.Member;
import dongho.classflix.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberJpaRepository;


    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberJpaRepository.save(member);
        return member.getId();
    }

    // 회원 전체 조회
    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberJpaRepository.findAll();
    }

    // 회원 하나 조회
    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberJpaRepository.findById(memberId).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<Member> findByName(String memberName) {
        if (memberName == null) {
            throw new NullPointerException("회원 이름이 입력되지 않았습니다.");
        }
        return memberJpaRepository.findByUserName(memberName);
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberJpaRepository.findByUserName(member.getUserName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
