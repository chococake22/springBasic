package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component

public class OrderServiceImpl implements OrderService {

    // final을 넣을 경우 생성자에서 값이 들어오지 않으면 오류를 알려준다. (누락을 막을 수 있다)
    private final MemberRepository memberRepository;
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy;

    // 위의 코드는 추상뿐 아니라 구체 클래스에도 의존한 상태이다.
    // 따라서 클라이언트의 코드를 수정해야하는 번거로움이 있다.
    // 이를 해결하기 위해서는 추상에만 의존하게 변경을 하면 된다.

    // 이렇게 하면 추상 인터페이스에만 의존함    -> 근데 이렇게 하면 test에서 오류가 난다.
    // 이유는 이렇게만 하면 아무 것도 할당이 되어있지 않기 때문이다. 그래서 null인 것이다.
    // private DiscountPolicy discountPolicy;

    // 해결하려면 누군가가 discountPolicy에다가 객체를 생성하고 주입해주어야 한다.

    // 생성자 주입은 호출 시점에 딱 한번만 호출되는 것이 보장된다
    // 그래서 불변, 필수 의존관계에 사용된다.
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
