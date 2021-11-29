package nicomed.tms.projectplanner.converter;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.enums.Format;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.util.Objects;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Component
public class FormatConverter implements AttributeConverter<Format, String> {

    private final ExceptionTestService exceptionTestService;


    @Override
    public String convertToDatabaseColumn(Format format) {
        if (isNull(format)) {
            exceptionTestService.throwTestFormatConvertingException(Format.class);
        }
        return format.getName();
    }

    @Override
    public Format convertToEntityAttribute(String name) {
        if (StringUtils.isEmpty(name)) {
            exceptionTestService.throwTestFormatConvertingException(Format.class);
        }
        Format format = Format.getByValue(name);
        if (Objects.isNull(format)) {
            exceptionTestService.throwTestFormatConvertingException(Format.class);
        }
        return format;
    }
}
