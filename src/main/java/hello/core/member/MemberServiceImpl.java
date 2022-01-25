package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    // 구현 객체를 선택해줘야 한다.
    private final MemberRepository memberRepository;

    // 생성자 자동으로 의존관계를 주입한다
    // 지금은 MemoryMemberRepository를 컴포넌트로 해서 빈으로 등록이 되었기 때문에 MemoryMemberRepository를 자동으로 주입한다.
    // ac.getBean(MemberRepository.class)랑 같은 역할
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
