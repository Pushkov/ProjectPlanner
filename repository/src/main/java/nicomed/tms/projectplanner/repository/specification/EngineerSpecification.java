package nicomed.tms.projectplanner.repository.specification;

import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.entity.Engineer_;
import nicomed.tms.projectplanner.enums.Status;
import nicomed.tms.projectplanner.repository.specification.filter.EngineerFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface EngineerSpecification extends Specification<Engineer> {

    static Specification<Engineer> hasFirstName(String value) {
        return (root, cq, cb) -> cb.equal(cb.lower(root.get(Engineer_.FIRST_NAME)), value.toLowerCase());
    }

    static Specification<Engineer> firstNameLike(String value) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get(Engineer_.FIRST_NAME)), "%" + value.toLowerCase() + "%");
    }

    static Specification<Engineer> hasLastName(String value) {
        return (root, cq, cb) -> cb.equal(cb.lower(root.get(Engineer_.LAST_NAME)), value.toLowerCase());
    }

    static Specification<Engineer> lastNameLike(String value) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get(Engineer_.LAST_NAME)), "%" + value.toLowerCase() + "%");
    }

    static Specification<Engineer> hasStatus(Status value) {
        return (root, cq, cb) -> cb.equal(cb.lower(root.get(Engineer_.STATUS)), value);
    }

    static Specification<Engineer> findByTerm(String term) {
        List<Specification<Engineer>> specList = new ArrayList<>();
        specList.add(firstNameLike(term));
        specList.add(lastNameLike(term));

        return SpecificationComposer.compose(specList, Predicate.BooleanOperator.OR);
    }

    static EngineerSpecification.Builder builder() {
        return new EngineerSpecification.Builder();
    }

    class Builder extends SpecificationBuilder<Engineer, EngineerFilter> {
        @Override
        public Specification build() {
            List<Specification<Engineer>> specificationList = new ArrayList<>();
            if (StringUtils.isNotEmpty(filter.getTerm())) {
                specificationList.add(findByTerm(filter.getTerm()));
            }
            return SpecificationComposer.compose(specificationList);
        }
    }
}
