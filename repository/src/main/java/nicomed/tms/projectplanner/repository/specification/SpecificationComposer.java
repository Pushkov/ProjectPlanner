package nicomed.tms.projectplanner.repository.specification;

import nicomed.tms.projectplanner.entity.Engineer;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.List;

public class SpecificationComposer {

    static Specification<Engineer> compose(List<EngineerSpecification> specList, Predicate.BooleanOperator operator) {

        if (CollectionUtils.isEmpty(specList)) return null;

        Specification<Engineer> result = null;
        for (EngineerSpecification spec : specList) {
            if (result == null) {
                result = Specification.where(spec);
            } else if (Predicate.BooleanOperator.OR == operator) {
                result = result.or(spec);
            } else {
                result = result.and(spec);
            }
        }
        return result;


    }
}
