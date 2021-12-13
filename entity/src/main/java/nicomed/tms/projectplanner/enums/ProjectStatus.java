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
public enum ProjectStatus {
    IN_WORK("В работе"),
    COMPLETED("Выполнен"),
    NOT_START("Не приступали");

    private final String name;

    private static final Map<String, ProjectStatus> MAP = Arrays.stream(ProjectStatus.values())
            .collect(Collectors.toMap(ProjectStatus::getName, Function.identity()));

    public static ProjectStatus getByName(String name) {
        if (Objects.isNull(name)) {
            throw new RuntimeException("ProjectStatus name cannot by empty");
        }
        ProjectStatus projectStatus = MAP.get(name);
        if (Objects.isNull(projectStatus)) {
            throw new RuntimeException("Incorrect projectStatus name");
        }
        return projectStatus;
    }
}
