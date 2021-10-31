package nicomed.tms.projectplanner.converter;

import nicomed.tms.projectplanner.enums.Format;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;

@Component
public class FormatConverter implements AttributeConverter<Format, String> {
    @Override
    public String convertToDatabaseColumn(Format format) {
        return format.getName();
    }

    @Override
    public Format convertToEntityAttribute(String name) {
        return Format.getByValue(name);
    }
}
