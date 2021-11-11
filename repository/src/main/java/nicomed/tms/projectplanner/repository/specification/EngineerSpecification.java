package nicomed.tms.projectplanner.repository.specification;

import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.entity.Engineer_;
import nicomed.tms.projectplanner.enums.Status;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface EngineerSpecification extends Specification<Engineer> {

    static EngineerSpecification hasFirstName(String value) {
        return (root, cq, cb) -> cb.equal(cb.lower(root.get(Engineer_.FIRST_NAME)), value.toLowerCase());
    }

    static EngineerSpecification firstNameLike(String value) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get(Engineer_.FIRST_NAME)), "%" + value.toLowerCase() + "%");
    }

    static EngineerSpecification hasLastName(String value) {
        return (root, cq, cb) -> cb.equal(cb.lower(root.get(Engineer_.LAST_NAME)), value.toLowerCase());
    }

    static EngineerSpecification lastNameLike(String value) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get(Engineer_.LAST_NAME)), "%" + value.toLowerCase() + "%");
    }

    static EngineerSpecification hasStatus(Status value) {
        return (root, cq, cb) -> cb.equal(cb.lower(root.get(Engineer_.STATUS)), value);
    }

    static Specification<Engineer> findByTerm(String term) {
        List<EngineerSpecification> specList = new ArrayList<>();
        specList.add(firstNameLike(term));
        specList.add(lastNameLike(term));

        return SpecificationComposer.compose(specList, Predicate.BooleanOperator.OR);
    }


}
