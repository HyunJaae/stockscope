package me.hyeonjae.stockscope.common.aop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import me.hyeonjae.stockscope.common.annotation.Retry;

public class RetryAspectTest {
    
    @Test
    @DisplayName("Retry 어노테이션이 붙은 메서드는 최대 3번 재시도된다.")
    void retrySuccess() {
        // given
        TestService testService = new TestService();
        AspectJProxyFactory factory = new AspectJProxyFactory(testService);
        factory.addAspect(new RetryAspect());
        
        TestService proxy = factory.getProxy();

        // when & then
        Assertions.assertThatNoException()
            .isThrownBy(() -> proxy.retrySuccess());
    }

    @Test
    @DisplayName("재시도 횟수를 초과하면 예외가 발생한다.")
    void retryFailAfterMaxAttempts() {
        // given
        TestService testService = new TestService();
        AspectJProxyFactory factory = new AspectJProxyFactory(testService);
        factory.addAspect(new RetryAspect());
        TestService proxy = factory.getProxy();

        // when & then
        Assertions.assertThatThrownBy(() -> proxy.alwaysFail())
            .isInstanceOf(RuntimeException.class)
            .hasMessage("Maximum retry attempts reached");
    }

    static class TestService {
        private int successCount = 0;

        @Retry(maxAttempts = 3)
        public void retrySuccess() {
            if (successCount++ < 2) {
                throw new RuntimeException("Temporary failure");
            }
        }

        @Retry(maxAttempts = 3)
        public void alwaysFail() {
            throw new RuntimeException("Maximum retry attempts reached");
        }
    }
}
