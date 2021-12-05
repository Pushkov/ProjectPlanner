package nicomed.tms.projectplanner.repository.specification.filter;

import lombok.*;
import nicomed.tms.projectplanner.entity.PlanProject;
import nicomed.tms.projectplanner.repository.specification.PlanProjectSpecification;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PlanProjectFilter implements Filter<PlanProject> {

    private Integer year;
    private Integer month;
    private String depName;
    private String termProjectDes;
    private String termProjectName;
    private String termTaskName;

    @Override
    public Specification<PlanProject> getSpecification() {
        return PlanProjectSpecification.builder().filter(this).build();
    }
}
