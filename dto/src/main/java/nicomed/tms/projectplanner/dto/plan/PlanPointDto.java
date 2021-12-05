package nicomed.tms.projectplanner.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PlanPointDto {

    private Long id;
    private Integer year;
    private Integer month;
    private Long departmentId;
    private String departmentName;
    private String projectDesignation;
    private String projectName;
}
