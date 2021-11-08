package nicomed.tms.projectplanner.annotations;


import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.stereotype.Controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Controller
//@ConditionalOnProperty( name = "application.type.rest", havingValue = "true")
@ConditionalOnResource(resources = "classpath:templates/rest")
public @interface MyRest {
}
