package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.document.DocumentCreateDto;
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

@Transactional(readOnly = true)
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

    @Override
    public Collection<DocumentSignedDto> findAllSigned() {
        return getRepository().findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void save(DocumentCreateDto dto) {
        documentSignedRepository.save(mapToEntity(dto));
    }

    @Transactional
    @Override
    public void save(Long id, DocumentCreateDto dto) {
        DocumentSigned documentSigned = findEntityById(id);
        mapper.mapToEntity(documentSigned, dto);

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
        return null;
    }

    public DocumentSigned mapToEntity(DocumentCreateDto dto) {
        return mapper.mapToEntity(dto);
    }

    @Override
    public Class<DocumentSigned> getEntityClass() {
        return DocumentSigned.class;
    }
}
