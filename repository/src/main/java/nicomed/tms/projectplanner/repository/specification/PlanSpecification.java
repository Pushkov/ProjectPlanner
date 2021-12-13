package nicomed.tms.projectplanner.repository.specification;

import nicomed.tms.projectplanner.entity.Plan;
import nicomed.tms.projectplanner.entity.PlanPK_;
import nicomed.tms.projectplanner.entity.Plan_;
import nicomed.tms.projectplanner.repository.specification.filter.PlanFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface PlanSpecification extends Specification<Plan> {

    static Specification<Plan> hasYear(Integer value) {
        return (root, cq, cb) -> cb.equal(root.get(Plan_.id).get(PlanPK_.YEAR), value);
    }

    static Specification<Plan> hasMonth(Integer value) {
        return (root, cq, cb) -> cb.equal(root.get(Plan_.id).get(PlanPK_.MONTH), value);
    }

    static Specification<Plan> hasDepartment(Long value) {
        return (root, cq, cb) -> cb.equal(cb.lower(root.get(Plan_.id).get(PlanPK_.DEPARTMENT)), value);
    }

    static Specification<Plan> search(PlanFilter filter) {
        List<Specification<Plan>> specList = new ArrayList<>();
        if (filter.getYear() != null) {
            specList.add(hasYear(filter.getYear()));
        }
        if (filter.getMonth() != null) {
            specList.add(hasMonth(filter.getMonth()));
        }
        if (filter.getDepId() != null) {
            specList.add(hasDepartment(filter.getDepId()));
        }
        return SpecificationComposer.compose(specList, Predicate.BooleanOperator.AND);
    }

    static PlanSpecification.Builder builder() {
        return new PlanSpecification.Builder();
    }

    class Builder extends SpecificationBuilder<Plan, PlanFilter> {
        @Override
        public Specification build() {
            List<Specification<Plan>> specificationList = new ArrayList<>();
            if (filter.getYear() != null) {
                specificationList.add(hasYear(filter.getYear()));
            }
            if (filter.getMonth() != null) {
                specificationList.add(hasMonth(filter.getMonth()));
            }
            if (filter.getDepId() != null) {
                specificationList.add(hasDepartment(filter.getDepId()));
            }
            return SpecificationComposer.compose(specificationList);
        }
    }
}
