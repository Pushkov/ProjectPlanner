package nicomed.tms.projectplanner.repository.specification;

import nicomed.tms.projectplanner.entity.PlanProject;
import nicomed.tms.projectplanner.entity.PlanProject_;
import nicomed.tms.projectplanner.repository.specification.filter.PlanProjectFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface PlanProjectSpecification extends Specification<PlanProject> {

    static Specification<PlanProject> hasYear(Integer value) {
        return (root, cq, cb) -> cb.equal(root.get(PlanProject_.YEAR), value);
    }

    static Specification<PlanProject> hasMonth(Integer value) {
        return (root, cq, cb) -> cb.equal(root.get(PlanProject_.MONTH), value);
    }

    static Specification<PlanProject> hasDepartmentName(String value) {
        return (root, cq, cb) -> cb.equal(cb.lower(root.get(PlanProject_.DEPARTMENT_NAME)), value.toLowerCase());
    }

    static Specification<PlanProject> likeProjectDesignation(String value) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get(PlanProject_.PROJECT_DESIGNATION)), "%" + value.toLowerCase() + "%");
    }

    static Specification<PlanProject> likeProjectName(String value) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get(PlanProject_.PROJECT_NAME)), "%" + value.toLowerCase() + "%");
    }

    static Specification<PlanProject> likeTaskName(String value) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get(PlanProject_.TASK_NAME)), "%" + value.toLowerCase() + "%");
    }

    //    static Specification<PlanProject> search(Integer year, Integer month, String depName, String termProjectDes, String termProjectName, String termTaskName) {
    static Specification<PlanProject> search(PlanProjectFilter filter) {
        List<Specification<PlanProject>> specList = new ArrayList<>();
        if (filter.getYear() != null) {
            specList.add(hasYear(filter.getYear()));
        }
        if (filter.getMonth() != null) {
            specList.add(hasMonth(filter.getMonth()));
        }
        if (StringUtils.isNotEmpty(filter.getDepName())) {
            specList.add(hasDepartmentName(filter.getDepName()));
        }
        if (StringUtils.isNotEmpty(filter.getTermProjectDes())) {
            specList.add(likeProjectDesignation(filter.getTermProjectDes()));
        }
        if (StringUtils.isNotEmpty(filter.getTermProjectName())) {
            specList.add(likeProjectName(filter.getTermProjectName()));
        }
        if (StringUtils.isNotEmpty(filter.getTermTaskName())) {
            specList.add(likeTaskName(filter.getTermTaskName()));
        }
        return SpecificationComposer.compose(specList, Predicate.BooleanOperator.AND);
    }

    static PlanProjectSpecification.Builder builder() {
        return new PlanProjectSpecification.Builder();
    }

    class Builder extends SpecificationBuilder<PlanProject, PlanProjectFilter> {
        @Override
        public Specification build() {
            List<Specification<PlanProject>> specificationList = new ArrayList<>();
            if (filter.getYear() != null) {
                specificationList.add(hasYear(filter.getYear()));
            }
            if (filter.getMonth() != null) {
                specificationList.add(hasMonth(filter.getMonth()));
            }
            if (StringUtils.isNotEmpty(filter.getDepName())) {
                specificationList.add(hasDepartmentName(filter.getDepName()));
            }
            if (StringUtils.isNotEmpty(filter.getTermProjectDes())) {
                specificationList.add(likeProjectDesignation(filter.getTermProjectDes()));
            }
            if (StringUtils.isNotEmpty(filter.getTermProjectName())) {
                specificationList.add(likeProjectName(filter.getTermProjectName()));
            }
            if (StringUtils.isNotEmpty(filter.getTermTaskName())) {
                specificationList.add(likeTaskName(filter.getTermTaskName()));
            }
            return SpecificationComposer.compose(specificationList);
        }
    }
}
