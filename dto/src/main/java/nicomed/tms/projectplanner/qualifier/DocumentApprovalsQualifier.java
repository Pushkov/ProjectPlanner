package nicomed.tms.projectplanner.qualifier;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.document.DocumentApprovalsDto;
import nicomed.tms.projectplanner.entity.Document;
import nicomed.tms.projectplanner.entity.DocumentApprovals;
import nicomed.tms.projectplanner.entity.DocumentSigned;
import nicomed.tms.projectplanner.repository.DocumentRepository;
import org.mapstruct.Named;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DocumentApprovalsQualifier extends AbstractEntityQualifier<Document, Long> {

    private final DocumentRepository repository;

    @Override
    public JpaRepository<Document, Long> getRepository() {
        return repository;
    }

    @Named("documentApprovalsIsNull")
    public DocumentApprovalsDto getNull(Long id) {
        return null;
    }

    @Named("documentApprovalsBy")
    public DocumentApprovalsDto getBy(DocumentSigned document) {
        DocumentApprovals approvals = document.getDocumentApprovals();
        if (approvals == null) {
            return DocumentApprovalsDto.builder().build();
        }
        return DocumentApprovalsDto.builder()
                .designerId(approvals.getDesigner().getId())
                .designerSign(approvals.getDesignerSign())
                .verifierId(approvals.getVerifier().getId())
                .verifierSign(approvals.getVerifierSign())
                .normControlId(approvals.getNormControl().getId())
                .normControlSign(approvals.getNormControlSign())
                .build();
    }

}

