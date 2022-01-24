package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    // DB랑 연결되지 않아서 그냥 테스트용으로만 사용하기 위해서 만들었음
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);

    }

    @Override
    public Member findById(Long memberid) {
        return store.get(memberid);
    }
}
