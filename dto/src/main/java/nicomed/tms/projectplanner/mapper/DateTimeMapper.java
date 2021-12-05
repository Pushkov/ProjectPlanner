package nicomed.tms.projectplanner.mapper;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public interface DateTimeMapper {

    default String mapDateToString(OffsetDateTime offsetDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return offsetDateTime.format(formatter);
    }

    default OffsetDateTime mapStringToDate(String stringDateTime) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd", new Locale("ru"));
        return ZonedDateTime.parse(stringDateTime, format).toOffsetDateTime();

    }

    /*
String date =
  DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
  .withLocale(new Locale("ru"))
  .format(LocalDate.of(2014, 2, 28));
System.out.println(date); // output: 28 февраля 2014 г.

     */
}

