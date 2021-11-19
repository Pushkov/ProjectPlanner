package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.DocumentSignedDto;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.DocumentSigned;
import nicomed.tms.projectplanner.mapper.DocumentSignedMapper;
import nicomed.tms.projectplanner.repository.DocumentSignedRepository;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import nicomed.tms.projectplanner.repository.specification.SearcheableService;
import nicomed.tms.projectplanner.services.DocumentSignedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class DocumentSignedJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<DocumentSigned, Long> implements DocumentSignedService, SearcheableService<DocumentSigned> {

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

    public DocumentSignedDto findDtoById(Long id) {
        return documentSignedRepository.findById(id)
                .map(mapper::mapToDto)
                .orElseThrow(() -> new NoSuchElementException(""));
    }

    public List<DocumentSignedDto> findAllDto() {
        List<DocumentSignedDto> collect = documentSignedRepository.findAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
        log.warn("DocBasic qty: {}", collect.size());
        return collect;
    }

    @Override
    public Long count() {
        return documentSignedRepository.count();
    }
}
