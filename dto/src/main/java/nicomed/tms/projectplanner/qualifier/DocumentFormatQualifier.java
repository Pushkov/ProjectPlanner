package nicomed.tms.projectplanner.qualifier;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.document.format.DocumentFormatDto;
import nicomed.tms.projectplanner.entity.DocumentFormat;
import nicomed.tms.projectplanner.mapper.DocumentFormatMapper;
import nicomed.tms.projectplanner.repository.DocumentFormatRepository;
import org.mapstruct.Named;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class DocumentFormatQualifier extends AbstractEntityQualifier<DocumentFormat, Long> {

    private final DocumentFormatRepository repository;
    private final DocumentFormatMapper documentFormatMapper;

    @Override
    public JpaRepository<DocumentFormat, Long> getRepository() {
        return repository;
    }

    @Named("documentFormatBy")
    public DocumentFormat getBy(DocumentFormatDto dto) {
        if (dto == null) {
            return null;
        }
        DocumentFormat format = getEntity(dto.getId());
        if (Objects.isNull(format)) {
            format = documentFormatMapper.mapToEntity(dto);
            repository.save(format);
        } else {
            format = getEntity(dto.getId());
        }
        return format;
    }

    @Override
    protected DocumentFormat getEntity(Long id) {
        if (id == null) {
            return null;
        }
        return getRepository().findById(id).orElseThrow(() -> new NoSuchElementException(""));
    }
}

