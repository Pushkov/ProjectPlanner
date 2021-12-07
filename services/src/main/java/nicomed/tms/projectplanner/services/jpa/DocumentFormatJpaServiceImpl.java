package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.document.format.DocumentFormatDto;
import nicomed.tms.projectplanner.entity.DocumentFormat;
import nicomed.tms.projectplanner.mapper.DocumentFormatMapper;
import nicomed.tms.projectplanner.repository.DocumentFormatRepository;
import nicomed.tms.projectplanner.services.DocumentFormatService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@JpaImpl
public class DocumentFormatJpaServiceImpl extends AbstractJpaService<DocumentFormatDto, DocumentFormat, Long> implements DocumentFormatService {

    private final DocumentFormatRepository documentFormatRepository;
    private final DocumentFormatMapper mapper;

    @Override
    public JpaRepository<DocumentFormat, Long> getRepository() {
        return documentFormatRepository;
    }

    @Override
    public Collection<DocumentFormatDto> findByDocumentId(Long id) {
        return documentFormatRepository.findAllByDocument_Id(id).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void save(DocumentFormat documentFormat) {
        documentFormatRepository.save(documentFormat);
    }

    @Override
    public DocumentFormatDto mapToDto(DocumentFormat entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public DocumentFormat mapToEntity(DocumentFormatDto dto) {
        return mapper.mapToEntity(dto);
    }

    @Override
    public Class<DocumentFormat> getEntityClass() {
        return DocumentFormat.class;
    }
}
