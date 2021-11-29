package nicomed.tms.projectplanner.dto.project;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nicomed.tms.projectplanner.dto.MemoDto;
import nicomed.tms.projectplanner.dto.TechnicalTaskDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class ProjectDto extends ProjectSimpleDto {

    private List<ProjectSimpleDto> basicProject;
    private List<ProjectSimpleDto> projects;

    //    private List<PlanPoint> planPoints;
//
//    private List<DocumentSimpleDto> documentsDto;
    //
    private String departmentName;
    private String workshopName;

    private ProjectApprovalsDto projectApprovalsDto;

    private TechnicalTaskDto task;
    private MemoDto memo;
//
}
