package nicomed.tms.projectplanner.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Format {
    ND("БЧ"),
    A4("А4"),
    A4_3("А4х3"),
    A3("А3"),
    A3_3("А3х3"),
    A2("А2"),
    A2_3("А2х3"),
    A1("А1"),
    A1_3("А1х3"),
    A0("А0");

    private final String format;

    private static final Map<String, Format> MAP = Arrays.stream(Format.values())
            .collect(Collectors.toMap(Format::getFormat, Function.identity()));

    public static Format getByName(String name) {
        if (Objects.isNull(name)) {
            throw new RuntimeException("Format name cannot by empty");
        }
        Format format = MAP.get(name);
        if (Objects.isNull(format)) {
            throw new RuntimeException("Incorrect format name");
        }
        return format;
    }
}
