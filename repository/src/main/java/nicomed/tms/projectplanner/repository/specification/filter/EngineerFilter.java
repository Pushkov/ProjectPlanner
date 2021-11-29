package nicomed.tms.projectplanner.repository.specification.filter;

import lombok.Builder;
import lombok.Value;
import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.repository.specification.EngineerSpecification;
import org.springframework.data.jpa.domain.Specification;

@Builder
@Value
public class EngineerFilter implements Filter<Engineer> {

    private String term;

    @Override
    public Specification<Engineer> getSpecification() {
        return EngineerSpecification.builder().filter(this).build();
    }
}
