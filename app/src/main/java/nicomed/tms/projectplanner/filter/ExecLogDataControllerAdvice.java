package nicomed.tms.projectplanner.filter;

import lombok.Getter;
import lombok.Setter;
import nicomed.tms.projectplanner.services.aspect.AspectLogService;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import static java.lang.String.valueOf;

@Getter
@Setter
@ControllerAdvice
public class ExecLogDataControllerAdvice implements ResponseBodyAdvice<Object>, AspectLogService {

    private long time = -1;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (time >= 0) {
            response.getHeaders().add("logtime", valueOf(time));
            time = -1;
        }
        return body;
    }

    @Override
    public void setTime(Long time) {
        this.time = time;
    }
}
