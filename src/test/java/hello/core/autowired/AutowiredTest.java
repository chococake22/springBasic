package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.mockito.internal.creation.SuspendMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        // Member는 스프링 빈이 아니다
        @Autowired(required = false)    // false로 하면 호출 자체가 안된다 / 자동 주입할 대상이 없으면 수정자 메서드가 호출이 안된다.
        public void setNoBean1(Member noBean1) {
            System.out.println("nobean1 = " + noBean1);
        }
        // Member는 스프링 빈으로 등록되지 않았다 -> 자동 주입을 할 수가 없는 것이다.

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }

    }

}
