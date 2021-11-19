package nicomed.tms.projectplanner.webaspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class WebControlerAspect {


    @Around("@annotation(LogExecutionTimeInWeb)")
    public Object logExecutionTimeInWeb(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        ResponseEntity<?> target = (ResponseEntity<?>) joinPoint.getTarget();
        target.getHeaders().add("MyTime", String.valueOf(executionTime));

        log.warn("{} executed in {}ms", joinPoint.getSignature(), executionTime);
        return proceed;
    }

}
