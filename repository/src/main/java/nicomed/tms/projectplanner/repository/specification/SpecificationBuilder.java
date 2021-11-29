package nicomed.tms.projectplanner.repository.specification;

import nicomed.tms.projectplanner.repository.specification.filter.Filter;
import org.springframework.data.jpa.domain.Specification;

public abstract class SpecificationBuilder<T, F extends Filter<T>> {

    protected F filter;

    public SpecificationBuilder<T, F> filter(F filter) {
        this.filter = filter;
        return this;
    }

    public abstract Specification<T> build();
}
