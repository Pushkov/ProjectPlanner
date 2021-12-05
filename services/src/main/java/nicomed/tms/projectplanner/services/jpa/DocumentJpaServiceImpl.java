package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.document.DocumentDto;
import nicomed.tms.projectplanner.dto.document.DocumentSignedDto;
import nicomed.tms.projectplanner.dto.document.DocumentSimpleDto;
import nicomed.tms.projectplanner.entity.Document;
import nicomed.tms.projectplanner.entity.DocumentSigned;
import nicomed.tms.projectplanner.mapper.DocumentMapper;
import nicomed.tms.projectplanner.mapper.DocumentSignedMapper;
import nicomed.tms.projectplanner.repository.DocumentRepository;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import nicomed.tms.projectplanner.repository.specification.SearcheableService;
import nicomed.tms.projectplanner.repository.specification.filter.DocumentFilter;
import nicomed.tms.projectplanner.services.DocumentService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static nicomed.tms.projectplanner.repository.specification.DocumentSpecification.findByTerm;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@JpaImpl
public class DocumentJpaServiceImpl extends AbstractDoubleDtoJpaService<DocumentDto, DocumentSimpleDto, Document, Long>
        implements DocumentService, SearcheableService<Document> {

    private final DocumentRepository documentRepository;
    private final DocumentMapper mapper;
    private final DocumentSignedMapper signedMapper;

    @Override
    public JpaRepository<Document, Long> getRepository() {
        return documentRepository;
    }

    @Override
    public SearchableRepository<Document, ?> getSearchRepository() {
        return documentRepository;
    }

    @Override
    public DocumentSignedDto findById(Long id) {
        Document document = findEntityById(id);
        if (document instanceof DocumentSigned) {
            return signedMapper.mapToDto((DocumentSigned) document);
        }
        return mapper.mapToDto(document);
    }

    @Override
    public List<DocumentSimpleDto> search(DocumentFilter engineerFilter) {
        Specification<Document> specification = findByTerm(engineerFilter.getTerm());
        return documentRepository.findAll(specification).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DocumentSimpleDto mapToSimpleDto(Document entity) {
        return mapper.mapToSimpleDto(entity);
    }

    @Override
    public DocumentDto mapToDto(Document entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public Document mapToEntity(DocumentDto dto) {
        return mapToEntity(dto);
    }

    @Override
    public Class<Document> getEntityClass() {
        return Document.class;
    }
}
