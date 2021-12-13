package nicomed.tms.projectplanner.repository.specification.filter;

import lombok.*;
import nicomed.tms.projectplanner.entity.Plan;
import nicomed.tms.projectplanner.repository.specification.PlanSpecification;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PlanFilter implements Filter<Plan> {

    private Integer year;
    private Integer month;
    private Long depId;

    @Override
    public Specification<Plan> getSpecification() {
        return PlanSpecification.builder().filter(this).build();
    }
}
