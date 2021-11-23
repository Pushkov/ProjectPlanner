package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.document.DocumentSignedDto;
import nicomed.tms.projectplanner.dto.document.DocumentSimpleDto;
import nicomed.tms.projectplanner.entity.DocumentSigned;
import nicomed.tms.projectplanner.mapper.DocumentSignedMapper;
import nicomed.tms.projectplanner.repository.DocumentSignedRepository;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import nicomed.tms.projectplanner.repository.specification.SearcheableService;
import nicomed.tms.projectplanner.services.DocumentSignedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class DocumentSignedJpaServiceImpl extends AbstractDoubleDtoJpaService<DocumentSignedDto, DocumentSimpleDto, DocumentSigned, Long> implements DocumentSignedService, SearcheableService<DocumentSigned> {

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

    @Transactional
    @Override
    public Collection<DocumentSignedDto> findAllSigned() {
        return getRepository().findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DocumentSimpleDto mapToSimpleDto(DocumentSigned entity) {
        return mapper.mapToSimpleDto(entity);
    }

    @Override
    public DocumentSignedDto mapToDto(DocumentSigned entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public DocumentSigned mapToEntity(DocumentSignedDto dto) {
        return mapper.mapToEntity(dto);
    }
}
