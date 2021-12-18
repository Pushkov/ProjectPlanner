package nicomed.tms.projectplanner.qualifier;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.document.DocumentDto;
import nicomed.tms.projectplanner.dto.document.DocumentSignedDto;
import nicomed.tms.projectplanner.dto.document.DocumentSimpleDto;
import nicomed.tms.projectplanner.entity.Document;
import nicomed.tms.projectplanner.repository.DocumentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DocumentQualifier extends AbstractEntityQualifier<Document, Long> {

    private final DocumentRepository repository;

    @Override
    public JpaRepository<Document, Long> getRepository() {
        return repository;
    }

    public Document getBy(DocumentDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }

    public Document getBySimple(DocumentSimpleDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }

    public Document getBySigned(DocumentSignedDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }
}

