package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
//@Component
public class TimeTraceAop {

    /* SpringConfig.java에서 "@Bean public TimeTraceAop" 만들 때는
     * "@Compoenet public class TimeTraceAop" 에서 Bean이 중복 생성되므로
     * 둘 중 한쪽 Bean은 제거해야됨
     * 또는 application.properties에 spring.main.allow-bean-definition-overriding=true 옵션 추가
     * */

    @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }
}
