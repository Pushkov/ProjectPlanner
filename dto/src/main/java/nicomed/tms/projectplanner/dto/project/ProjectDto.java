package nicomed.tms.projectplanner.dto.project;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nicomed.tms.projectplanner.dto.memo.MemoForListDto;
import nicomed.tms.projectplanner.dto.task.TechnicalTaskForListDto;

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
    private Long departmentId;
    private String departmentName;
    private Long workshopId;
    private String workshopName;

    private ProjectApprovalsDto projectApprovalsDto;

    private TechnicalTaskForListDto task;
    private MemoForListDto memo;
//
}
