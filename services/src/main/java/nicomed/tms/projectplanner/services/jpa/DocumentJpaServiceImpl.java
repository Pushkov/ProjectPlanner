package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.document.DocumentDto;
import nicomed.tms.projectplanner.entity.Document;
import nicomed.tms.projectplanner.mapper.DocumentMapper;
import nicomed.tms.projectplanner.repository.DocumentRepository;
import nicomed.tms.projectplanner.repository.specification.DocumentSpecification;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import nicomed.tms.projectplanner.repository.specification.SearcheableService;
import nicomed.tms.projectplanner.repository.specification.filter.DocumentFilter;
import nicomed.tms.projectplanner.services.DocumentService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@JpaImpl
public class DocumentJpaServiceImpl extends AbstractJpaService<DocumentDto, Document, Long>
        implements DocumentService, SearcheableService<Document> {

    private final DocumentRepository documentRepository;
    private final DocumentMapper mapper;

    @Override
    public JpaRepository<Document, Long> getRepository() {
        return documentRepository;
    }

    @Override
    public SearchableRepository<Document, ?> getSearchRepository() {
        return documentRepository;
    }

    @Override
    public List<DocumentDto> search(DocumentFilter engineerFilter) {
        Specification<Document> specification = DocumentSpecification.findByTerm(engineerFilter.getTerm());
        return documentRepository.findAll(specification).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DocumentDto mapToDto(Document entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public Document mapToEntity(DocumentDto dto) {
        return mapper.mapToEntity(dto);
    }

}
