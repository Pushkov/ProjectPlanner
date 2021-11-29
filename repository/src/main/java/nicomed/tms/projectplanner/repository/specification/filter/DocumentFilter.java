package nicomed.tms.projectplanner.repository.specification.filter;

import lombok.Builder;
import lombok.Value;
import nicomed.tms.projectplanner.entity.Document;
import nicomed.tms.projectplanner.repository.specification.DocumentSpecification;
import org.springframework.data.jpa.domain.Specification;

@Builder
@Value
public class DocumentFilter implements Filter<Document> {

    private String term;

    @Override
    public Specification<Document> getSpecification() {
        return DocumentSpecification.builder().filter(this).build();
    }
}
