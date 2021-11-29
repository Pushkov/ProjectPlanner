package nicomed.tms.projectplanner.services.aspect;

import nicomed.tms.listener.AuditLogService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
public class LogEntityAspect {

    @Autowired
    private AuditLogService auditLogService;


    @After("@annotation(loggegMethod)")
    public void logAction(JoinPoint joinPoint, LoggegMethod loggegMethod) {
        String activity = loggegMethod.activity();
        String msg = loggegMethod.value();

        String[] names = StringUtils.substringsBetween(msg, "{", "}");
        if (!ArrayUtils.isEmpty(names)) {
            msg = prepareMsg(msg, names, joinPoint);
        }
        auditLogService.createLogs(activity, msg);
    }

    private String prepareMsg(String msg, String[] names, JoinPoint joinPoint) {
        int size = names.length;
        String[] values = new String[size];

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            Object value = getParameter(joinPoint, name);

            String valueStr = Objects.isNull(value) ? "UNDEFINED" : String.valueOf(value);
            values[i] = valueStr;
            names[i] = "{" + name + "}";
        }

        return StringUtils.replaceEach(msg, names, values);
    }

    private Object getParameter(JoinPoint joinPoint, String name) {
        if (Objects.nonNull(joinPoint) && joinPoint.getSignature() instanceof MethodSignature
                && Objects.nonNull(name)) {

            MethodSignature method = (MethodSignature) joinPoint.getSignature();
            String[] params = method.getParameterNames();

            for (int i = 0; i < params.length; i++) {
                if (Objects.nonNull(params[i]) && params[i].equals(name)) {
                    Object[] objects = joinPoint.getArgs();
                    return objects[i];
                }
            }
        }
        return null;
    }
}
