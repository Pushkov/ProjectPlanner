package nicomed.tms.projectplanner.mapper;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public interface DateTimeMapper {

    default String mapDateToString(OffsetDateTime offsetDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return offsetDateTime.format(formatter);
    }
}
