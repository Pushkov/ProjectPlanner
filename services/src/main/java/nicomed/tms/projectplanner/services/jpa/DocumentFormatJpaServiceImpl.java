package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.DocumentFormat;
import nicomed.tms.projectplanner.repository.DocumentFormatRepository;
import nicomed.tms.projectplanner.services.DocumentFormatService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DocumentFormatJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<DocumentFormat, Long> implements DocumentFormatService {

    private final DocumentFormatRepository documentFormatRepository;

    @Override
    public JpaRepository<DocumentFormat, Long> getRepository() {
        return documentFormatRepository;
    }
}
