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
    ND("БЧ", 0f),
    A4("А4", 0.125f),
    A4_3("А4х3", 0.375f),
    A3("А3", 0.25f),
    А3_3("А3х3", 0.75f),
    А2("А2", 0.5f),
    А2_3("А2х3", 1.5f),
    А1("А1", 1f),
    А1_3("А1х3", 3f),
    А0("А0", 2f);

    private final String name;
    private final float size;

    private static final Map<String, Format> MAP = Arrays.stream(Format.values())
            .collect(Collectors.toMap(Format::getName, Function.identity()));

    public static Format getByValue(String value) {
        if (Objects.isNull(value)) {
            throw new RuntimeException("Format name cannot by empty");
        }
        Format format = MAP.get(value);
        if (Objects.isNull(format)) {
            throw new RuntimeException("Incorrect format name");
        }
        return format;
    }
}
