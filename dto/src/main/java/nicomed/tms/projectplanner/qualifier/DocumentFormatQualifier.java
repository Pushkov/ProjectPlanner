package nicomed.tms.projectplanner.qualifier;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.document.format.DocumentFormatDto;
import nicomed.tms.projectplanner.entity.DocumentFormat;
import nicomed.tms.projectplanner.repository.DocumentFormatRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DocumentFormatQualifier extends AbstractEntityQualifier<DocumentFormat, Long> {

    private final DocumentFormatRepository repository;

    @Override
    public JpaRepository<DocumentFormat, Long> getRepository() {
        return repository;
    }

    public DocumentFormat getBy(DocumentFormatDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }

}

