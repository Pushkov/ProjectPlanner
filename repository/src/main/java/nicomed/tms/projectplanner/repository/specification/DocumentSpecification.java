package nicomed.tms.projectplanner.repository.specification;

import nicomed.tms.projectplanner.entity.Document;
import nicomed.tms.projectplanner.entity.Document_;
import nicomed.tms.projectplanner.repository.specification.filter.DocumentFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface DocumentSpecification extends Specification<Document> {

    static Specification<Document> nameLike(String value) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get(Document_.NAME)), "%" + value.toLowerCase() + "%");
    }

    static Specification<Document> designationLike(String value) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get(Document_.DESIGNATION)), "%" + value.toLowerCase() + "%");
    }

    static Specification<Document> findByTerm(String term) {
        List<Specification<Document>> specList = new ArrayList<>();
        specList.add(nameLike(term));
        specList.add(designationLike(term));

        return SpecificationComposer.compose(specList, Predicate.BooleanOperator.OR);
    }

    static DocumentSpecification.Builder builder() {
        return new DocumentSpecification.Builder();
    }

    class Builder extends SpecificationBuilder<Document, DocumentFilter> {
        @Override
        public Specification build() {
            List<Specification<Document>> specificationList = new ArrayList<>();
            if (StringUtils.isNotEmpty(filter.getTerm())) {
                specificationList.add(findByTerm(filter.getTerm()));
            }
            return SpecificationComposer.compose(specificationList);
        }
    }
}
