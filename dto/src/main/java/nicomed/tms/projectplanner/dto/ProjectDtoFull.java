package nicomed.tms.projectplanner.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ProjectDtoFull {

    private Long id;
    private String designation;
    private String name;

    private List<ProjectDtoShort> basicProject;
    private List<ProjectDtoShort> projects;

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
