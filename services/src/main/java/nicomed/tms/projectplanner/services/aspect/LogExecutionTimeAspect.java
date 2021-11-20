package nicomed.tms.projectplanner.services.aspect;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@RequiredArgsConstructor
@Component
@Getter
@Setter
public class LogExecutionTimeAspect {

    private final AspectLogService aspectLogService;

    @Around("@annotation(nicomed.tms.projectplanner.services.aspect.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
//        System.out.println(" ********** ASPECT ************");
        aspectLogService.setTime(executionTime);
        return proceed;
    }
}
