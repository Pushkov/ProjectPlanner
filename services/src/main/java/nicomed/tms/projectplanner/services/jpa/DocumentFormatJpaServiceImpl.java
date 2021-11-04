package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.DocumentFormat;
import nicomed.tms.projectplanner.repository.DocumentFormatRepository;
import nicomed.tms.projectplanner.services.DocumentFormatService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class DocumentFormatJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<DocumentFormat, Long> implements DocumentFormatService {

    private final DocumentFormatRepository documentFormatRepository;

    @Override
    public DocumentFormat findById(Long id) {
        return documentFormatRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void save(DocumentFormat documentFormat) {
        documentFormatRepository.save(documentFormat);
    }

    @Override
    public Collection<DocumentFormat> findAll() {
        return documentFormatRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        documentFormatRepository.deleteById(id);
    }

    @Override
    public JpaRepository<DocumentFormat, Long> getRepository() {
        return documentFormatRepository;
    }
}
