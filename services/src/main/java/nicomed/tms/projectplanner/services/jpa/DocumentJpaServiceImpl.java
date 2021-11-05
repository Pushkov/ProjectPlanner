package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Document;
import nicomed.tms.projectplanner.repository.DocumentRepository;
import nicomed.tms.projectplanner.services.DocumentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DocumentJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Document, Long> implements DocumentService {

    private final DocumentRepository documentRepository;

    @Override
    public JpaRepository<Document, Long> getRepository() {
        return documentRepository;
    }
}
