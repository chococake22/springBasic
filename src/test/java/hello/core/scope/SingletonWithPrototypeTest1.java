package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(2);

        // 싱글톤이니까 같다
        System.out.println(clientBean1 == clientBean2);

    }

    @Scope("singleton")
    static class ClientBean {

        // private final PrototypeBean prototypeBean;

        // 스프링 컨테이너를 생성하면서 자동으로 빈이 만들어진다.
        // 이때 의존주입이 되어야 하기 때문에 스프링 컨테이너에 prototypeBean를 요청하게 된다.
        // 그래서 이미 할당이 된다.
        // 결과적으로 생성 시점에 이미 주입이 된다는 것
        // 그래서 client1이 로직을 출력하고 client2가 다시 로직을 출력하면 같은 prototypeBean를 사용하게 된다.

        // 그럼 어떻게 하는 것이 좋을까?
        // 사용할 때마다 새로 빈을 생성해서 사용되어야 한다.

//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        @Autowired
        ApplicationContext applicationContext;


        public int logic() {
            // 프로토타입을 직접 받는 방법이 있다.
            // 하지만 이 방법은 너무 복잡하고 좋은 코드가 아니다.
            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }

    }


    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }


}
