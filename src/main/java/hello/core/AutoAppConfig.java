package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // -> 스프링 빈으로 끌어올리기 위해서 사용 -> @Component가 붙은 클래스를 찾아서 자동으로 스프링 빈 등록을 해준다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // @Configuration이 붙은 것들은 일단 빈 등록에서 제외를 해준다.
        // 그 이유는 @Configuration도 @Component가 붙어있기 때문에 자동으로 빈으로 등록되기 때문이다.
        // 일단 강의에서는 @ComponentScan에 영향을 직접적으로 받는 것들 위주로 설명해야한다.
)
public class AutoAppConfig {




}
