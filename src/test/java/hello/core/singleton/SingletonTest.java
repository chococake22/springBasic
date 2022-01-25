package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회 : 호출할 때마다 객체를 생성하는지 알아보자
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회를 한번 더
        MemberService memberService2 = appConfig.memberService();

        // 1과 2가 참조값이 같은지 다른지 확인해보기
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // 서로 다른 참조값을 가지고 있다. -> 요청할 때마다 객체가 계속 생성이 되는 것

        // memberService1은 memberService2랑 다르다
        assertThat(memberService1).isNotSameAs(memberService2);
    }


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용하기")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // 둘이 같은지 확인을 해보자
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
        // same -> == 비교
        // equal -> equals 비교
   }


    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        // AppConfig appConfig = new AppConfig();

        // 싱글톤 컨테이너 생성하기(스프링 컨테이너랑 같은 말이다)
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 1과 2가 참조값이 같은지 다른지 확인해보기
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // 서로 다른 참조값을 가지고 있다. -> 요청할 때마다 객체가 계속 생성이 되는 것

        // memberService1은 memberService2랑 같은 객체를 참조하고 있다.
        assertThat(memberService1).isSameAs(memberService2);
    }
}


