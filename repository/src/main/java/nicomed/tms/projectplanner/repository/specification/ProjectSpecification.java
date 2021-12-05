package nicomed.tms.projectplanner.repository.specification;

import nicomed.tms.projectplanner.entity.Project;
import nicomed.tms.projectplanner.entity.Project_;
import nicomed.tms.projectplanner.repository.specification.filter.ProjectFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface ProjectSpecification extends Specification<Project> {

    static Specification<Project> hasName(String value) {
        return (root, cq, cb) -> cb.equal(cb.lower(root.get(Project_.NAME)), value.toLowerCase());
    }

    static Specification<Project> nameLike(String value) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get(Project_.NAME)), "%" + value.toLowerCase() + "%");
    }

    static Specification<Project> hasDesignation(String value) {
        return (root, cq, cb) -> cb.equal(cb.lower(root.get(Project_.DESIGNATION)), value.toLowerCase());
    }

    static Specification<Project> designationLike(String value) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get(Project_.DESIGNATION)), "%" + value.toLowerCase() + "%");
    }


    static Specification<Project> findByTerm(String term) {
        List<Specification<Project>> specList = new ArrayList<>();
        specList.add(nameLike(term));
        specList.add(designationLike(term));

        return SpecificationComposer.compose(specList, Predicate.BooleanOperator.OR);
    }

    static ProjectSpecification.Builder builder() {
        return new ProjectSpecification.Builder();
    }

    class Builder extends SpecificationBuilder<Project, ProjectFilter> {
        @Override
        public Specification build() {
            List<Specification<Project>> specificationList = new ArrayList<>();
            if (StringUtils.isNotEmpty(filter.getTerm())) {
                specificationList.add(findByTerm(filter.getTerm()));
            }
            return SpecificationComposer.compose(specificationList);
        }
    }
}
