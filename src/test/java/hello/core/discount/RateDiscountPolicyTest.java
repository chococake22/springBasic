package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o() {
        // given
        // 일단 VIP인 member 회원이 있다고 했을 때
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        // when
        // 이 member는 10000의 금액을 구매한다면 얼마나 할인이 될까
        int discount = discountPolicy.discount(member, 10000);

        // then
        // 할인된 가격이 아마 1000원이지 않을까
        Assertions.assertThat(discount).isEqualTo(1000);
    }


    @Test
    @DisplayName("VIP가 아니면 할인이 적용되면 안된다. ")
    void vip_x() {
        // given
        // 일단 VIP가 아닌 회원이 있다고 가정하고
        Member member = new Member(1L, "memberSo", Grade.BASIC);

        // when
        // 이 member가 10000원의 금액을 구매한다면 할인이 될까??
        int discount = discountPolicy.discount(member, 10000);

        // then
        // 할인된 가격은 0원이어야 한다.
        Assertions.assertThat(discount).isEqualTo(0);
    }
}