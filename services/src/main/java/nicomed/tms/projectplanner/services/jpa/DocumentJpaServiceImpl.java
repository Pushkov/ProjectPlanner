package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.DocumentDto;
import nicomed.tms.projectplanner.entity.BaseEntity;
import nicomed.tms.projectplanner.entity.Document;
import nicomed.tms.projectplanner.mapper.DocumentMapper;
import nicomed.tms.projectplanner.repository.DocumentRepository;
import nicomed.tms.projectplanner.services.DocumentService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@RequiredArgsConstructor
@JpaImpl
public class DocumentJpaServiceImpl<T extends BaseEntity<ID>, ID> extends AbstractJpaService<Document, Long> implements DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentMapper mapper;

    @Override
    public JpaRepository<Document, Long> getRepository() {
        return documentRepository;
    }

    @Override
    public List<DocumentDto> findAllDto() {
        return mapper.mapToListDto((List<Document>) findAll());
    }

    @Override
    public DocumentDto findDtoById(Long id) {
        Document document = findById(id);
        System.out.println("> " + document.getName());
        System.out.println("> " + document.getDocumentApprovals().getDesigner().getLastName());


        return mapper.mapToDto(document);
    }
}
