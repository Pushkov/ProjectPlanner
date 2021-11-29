package nicomed.tms.projectplanner.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PlanDto {

    private int year;
    private int month;
    private String departmentName;
//    private List<PlanPointDto> planPoints;
}
