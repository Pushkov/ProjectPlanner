package nicomed.tms.projectplanner.repository.specification.filter;

import lombok.Builder;
import lombok.Value;
import nicomed.tms.projectplanner.entity.Project;
import nicomed.tms.projectplanner.repository.specification.ProjectSpecification;
import org.springframework.data.jpa.domain.Specification;

@Builder
@Value
public class ProjectFilter implements Filter<Project> {

    private String term;

    @Override
    public Specification<Project> getSpecification() {
        return ProjectSpecification.builder().filter(this).build();
    }
}
