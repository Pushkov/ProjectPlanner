package nicomed.tms.projectplanner.repository.specification;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.List;

public class SpecificationComposer {

    static <T> Specification<T> compose(List<Specification<T>> specList) {
        return compose(specList, Predicate.BooleanOperator.AND);
    }

    static <T> Specification<T> compose(List<Specification<T>> specList, Predicate.BooleanOperator operator) {

        if (CollectionUtils.isEmpty(specList)) return null;

        Specification<T> result = null;
        for (Specification<T> spec : specList) {
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
