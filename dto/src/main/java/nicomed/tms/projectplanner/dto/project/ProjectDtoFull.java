package nicomed.tms.projectplanner.dto.project;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nicomed.tms.projectplanner.dto.MemoDto;
import nicomed.tms.projectplanner.dto.TechnicalTaskDto;
import nicomed.tms.projectplanner.dto.document.DocumentDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class ProjectDtoFull extends ProjectDto {

    private List<ProjectDto> basicProject;
    private List<ProjectDto> projects;

    //    private List<PlanPoint> planPoints;
//
    private List<DocumentDto> documentsDto;
    //
    private String departmentName;
    private String workshopName;

    private ProjectApprovalsDto projectApprovalsDto;

    private TechnicalTaskDto task;
    private MemoDto memo;
//
}
