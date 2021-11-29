package nicomed.tms.projectplanner.repository.specification.filter;

import lombok.Builder;
import lombok.Value;
import nicomed.tms.projectplanner.entity.Department;
import nicomed.tms.projectplanner.repository.specification.DepartmentSpecification;
import org.springframework.data.jpa.domain.Specification;

@Builder
@Value
public class DepartmentFilter implements Filter<Department> {

    private String term;

    @Override
    public Specification<Department> getSpecification() {
        return DepartmentSpecification.builder().filter(this).build();
    }
}
