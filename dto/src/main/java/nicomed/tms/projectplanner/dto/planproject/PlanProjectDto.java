package nicomed.tms.projectplanner.dto.planproject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@SuperBuilder
@Data
public class PlanProjectDto {

    private Long id;
    private Integer year;
    private Integer month;
    private String departmentName;
    private String projectDesignation;
    private String projectName;
    private String taskName;
}
