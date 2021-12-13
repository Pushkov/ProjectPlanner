package nicomed.tms.projectplanner.dto.project;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nicomed.tms.projectplanner.dto.memo.MemoForListDto;
import nicomed.tms.projectplanner.dto.task.TechnicalTaskForListDto;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class ProjectSimpleDto {

    private Long id;
    private String designation;
    private String name;
    private Long workshopId;
    private String workshopName;
    private TechnicalTaskForListDto task;
    private MemoForListDto memo;
    private Long departmentId;
    private String departmentName;
    private String status;
}
