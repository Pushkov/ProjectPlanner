package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.DocumentDto;
import nicomed.tms.projectplanner.entity.BaseEntity;
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

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@JpaImpl
public class DocumentJpaServiceImpl<T extends BaseEntity<ID>, ID>
        extends AbstractJpaService<Document, Long>
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
    public List<DocumentDto> findAllDto() {
        return mapper.mapToListDto((List<Document>) findAll());
    }

    @Override
    public DocumentDto findDtoById(Long id) {
        return mapper.mapToDto(findById(id));
    }

    @Override
    public List<DocumentDto> search(DocumentFilter engineerFilter) {
        Specification<Document> specification = DocumentSpecification.findByTerm(engineerFilter.getTerm());
        return mapper.mapToListDto(documentRepository.findAll(specification));
    }
}
