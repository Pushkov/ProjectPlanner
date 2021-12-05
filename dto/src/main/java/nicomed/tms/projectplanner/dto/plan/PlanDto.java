package nicomed.tms.projectplanner.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PlanDto {

    private Integer year;
    private Integer month;
    private Long departmentId;
    private String departmentName;
    private List<PlanPointDto> planPointsDto;
}
