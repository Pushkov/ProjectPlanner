package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.document.DocumentSignedSimpleDto;
import nicomed.tms.projectplanner.entity.DocumentSigned;
import nicomed.tms.projectplanner.mapper.DocumentSignedMapper;
import nicomed.tms.projectplanner.repository.DocumentSignedRepository;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import nicomed.tms.projectplanner.repository.specification.SearcheableService;
import nicomed.tms.projectplanner.services.DocumentSignedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DocumentSignedJpaServiceImpl extends AbstractJpaService<DocumentSignedSimpleDto, DocumentSigned, Long> implements DocumentSignedService, SearcheableService<DocumentSigned> {

    private final DocumentSignedRepository documentSignedRepository;
    private final DocumentSignedMapper mapper;

    @Override
    public JpaRepository<DocumentSigned, Long> getRepository() {
        return documentSignedRepository;
    }

    @Override
    public SearchableRepository<DocumentSigned, ?> getSearchRepository() {
        return documentSignedRepository;
    }

    @Override
    public Long count() {
        return documentSignedRepository.count();
    }

    @Override
    public DocumentSignedSimpleDto mapToDto(DocumentSigned entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public DocumentSigned mapToEntity(DocumentSignedSimpleDto dto) {
        return mapper.mapToEntity(dto);
    }
}
