package nicomed.tms.projectplanner.dto.project;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nicomed.tms.projectplanner.dto.document.DocumentSimpleDto;
import nicomed.tms.projectplanner.dto.plan.PlanPointDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class ProjectDto extends ProjectSimpleDto {

    private List<ProjectSimpleDto> basicProject;
    private List<ProjectSimpleDto> projects;
    private List<PlanPointDto> planPoints;
    private List<DocumentSimpleDto> documentsDto;


    private ProjectApprovalsDto projectApprovalsDto;

}
