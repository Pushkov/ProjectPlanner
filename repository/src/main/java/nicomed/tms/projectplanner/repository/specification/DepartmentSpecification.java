package nicomed.tms.projectplanner.repository.specification;

import nicomed.tms.projectplanner.entity.Department;
import nicomed.tms.projectplanner.entity.Department_;
import nicomed.tms.projectplanner.repository.specification.filter.DepartmentFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface DepartmentSpecification extends Specification<Department> {

    static Specification<Department> nameLike(String value) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get(Department_.NAME)), "%" + value.toLowerCase() + "%");
    }

    static Specification<Department> fullNameLike(String value) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get(Department_.FULL_NAME)), "%" + value.toLowerCase() + "%");
    }

    static Specification<Department> findByTerm(String term) {
        List<Specification<Department>> specList = new ArrayList<>();
        specList.add(nameLike(term));
        specList.add(fullNameLike(term));
        return SpecificationComposer.compose(specList, Predicate.BooleanOperator.OR);
    }

    static DepartmentSpecification.Builder builder() {
        return new DepartmentSpecification.Builder();
    }

    class Builder extends SpecificationBuilder<Department, DepartmentFilter> {
        @Override
        public Specification build() {
            List<Specification<Department>> specificationList = new ArrayList<>();
            if (StringUtils.isNotEmpty(filter.getTerm())) {
                specificationList.add(findByTerm(filter.getTerm()));
            }
            return SpecificationComposer.compose(specificationList);
        }
    }
}
