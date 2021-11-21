package nicomed.tms.projectplanner.dto.project;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProjectDtoFull {

    private Long id;
    private String designation;
    private String name;

    private List<ProjectDto> basicProject;
    private List<ProjectDto> projects;

//    private List<PlanPoint> planPoints;
//
//    private List<DocumentSigned> documents;
//
//    private Department department;
//    private Workshop workshop;
//
//    private ProjectApprovals projectApprovals;
//
//    private TechnicalTask task;
//    private Memo memo;
//
}
