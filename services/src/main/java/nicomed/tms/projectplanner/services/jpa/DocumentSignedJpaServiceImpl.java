package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.document.DocumentCreateDto;
import nicomed.tms.projectplanner.dto.document.DocumentSignedDto;
import nicomed.tms.projectplanner.dto.document.DocumentSimpleDto;
import nicomed.tms.projectplanner.entity.*;
import nicomed.tms.projectplanner.mapper.DocumentApprovalsMapper;
import nicomed.tms.projectplanner.mapper.DocumentFormatMapper;
import nicomed.tms.projectplanner.mapper.DocumentSignedMapper;
import nicomed.tms.projectplanner.repository.DocumentSignedRepository;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import nicomed.tms.projectplanner.repository.specification.SearcheableService;
import nicomed.tms.projectplanner.services.DocumentSignedService;
import nicomed.tms.projectplanner.services.EngineerService;
import nicomed.tms.projectplanner.services.ProjectService;
import nicomed.tms.projectplanner.services.SheetFormatService;
import nicomed.tms.projectplanner.services.exception.ExceptionsProducer;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
@Service
public class DocumentSignedJpaServiceImpl extends AbstractDoubleDtoJpaService<DocumentSignedDto, DocumentSimpleDto, DocumentSigned, Long> implements DocumentSignedService, SearcheableService<DocumentSigned> {

    private final SheetFormatService sheetFormatService;
    private final DocumentSignedRepository documentSignedRepository;
    private final DocumentSignedMapper mapper;
    private final DocumentFormatMapper formatMapper;
    private final EngineerService engineerService;
    private final DocumentApprovalsMapper approvalsMapper;
    private final ProjectService projectService;

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
        DocumentSigned document = mapper.mapToEntity(dto);
        for (DocumentFormat format : document.getDocumentFormats()) {
            format.setDocument(document);
        }
//        List<DocumentFormatDto> formatDtos = dto.getDocumentFormatDto();
//        document.setDocumentFormats(getDocumentFormats(document, formatDtos));
//        document.setDocumentApprovals(getDocumentApprovals(dto.getDocumentApprovalsDto()));
        documentSignedRepository.save(document);
    }

    @Transactional
    @Override
    public void save(Long id, DocumentCreateDto dto) {
        DocumentSigned document = findEntityById(id);
        mapper.mapToEntity(document, dto);
        for (DocumentFormat format : document.getDocumentFormats()) {
            format.setDocument(document);
        }
//        List<DocumentFormatDto> formatDtos = dto.getDocumentFormatDto();
//        document.setDocumentFormats(getDocumentFormats(document, formatDtos));
//        document.setDocumentApprovals(getDocumentApprovals(dto.getDocumentApprovalsDto()));
        documentSignedRepository.save(document);
    }


//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    protected List<DocumentFormat> getDocumentFormats(Document document, List<DocumentFormatDto> formatDtos) {
//        List<DocumentFormat> formats = new ArrayList<>();
//        for (DocumentFormatDto formatDto : formatDtos) {
//            DocumentFormat documentFormat = formatMapper.mapToEntity(formatDto);
//            documentFormat.setDocument(document);
//            documentFormat.setFormat(
//                    sheetFormatService.findEntityByFormatName(formatDto.getFormatDto().getName())
//            );
//            formats.add(documentFormat);
//        }
//        return formats;
//    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    protected DocumentApprovals getDocumentApprovals(DocumentApprovalsDto dto) {
//        DocumentApprovals approvals = approvalsMapper.mapToEntity(dto);
//        if (dto.getDesignerId() != null) {
//            approvals.setDesigner(engineerService.findEntityById(dto.getDesignerId()));
//        }
//        if (dto.getVerifierId() != null) {
//            approvals.setVerifier(engineerService.findEntityById(dto.getVerifierId()));
//        }
//        if (dto.getNormControlId() != null) {
//            approvals.setNormControl(engineerService.findEntityById(dto.getNormControlId()));
//        }
//        return approvals;
//    }

    @Transactional
    @Override
    public void addProjectById(Long documentId, Long projectId) {
        addProject(findEntityById(documentId), projectService.findEntityById(projectId));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void addProject(Document document, Project project) {
        List<Project> projects = document.getProjects();
        if (projects != null) {
            document.getProjects().add(project);
        } else {
            document.setProjects(asList(project));
        }
        List<Document> documents = project.getDocuments();
        if (documents != null) {
            project.getDocuments().add(document);
        } else {
            project.setDocuments(asList(document));
        }
    }

    @Transactional
    @Override
    public void removeProjectById(Long documentId, Long projectId) {
        if (!Objects.isNull(documentId) && !Objects.isNull(projectId)) {
            removeProject(findEntityById(documentId), projectService.findEntityById(projectId));
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void removeProject(Document document, Project project) {
        List<Project> projects = document.getProjects();
        if (CollectionUtils.isNotEmpty(projects)) {
            projects.remove(project);
        }
        List<Document> documents = project.getDocuments();
        if (CollectionUtils.isNotEmpty(documents)) {
            documents.remove(document);
        }
    }

    @Transactional
    @Override
    public void signDesigner(Long doc_id, String login) {
        Engineer engineer = engineerService.findByLogin(login)
                .orElseThrow(() -> ExceptionsProducer.throwNotFoundByNameException(Engineer.class, login));
        DocumentApprovals documentApprovals = findEntityById(doc_id).getDocumentApprovals();
        if (!Objects.isNull(documentApprovals)) {
            documentApprovals.setDesigner(engineer);
            documentApprovals.setDesignerSign(LocalDate.now());
        }
    }

    @Transactional
    @Override
    public void signVerifier(Long doc_id, String login) {
        Engineer engineer = engineerService.findByLogin(login)
                .orElseThrow(() -> ExceptionsProducer.throwNotFoundByNameException(Engineer.class, login));
        DocumentApprovals documentApprovals = findEntityById(doc_id).getDocumentApprovals();
        if (!Objects.isNull(documentApprovals)) {
            documentApprovals.setVerifier(engineer);
            documentApprovals.setVerifierSign(LocalDate.now());
        }
    }

    @Transactional
    @Override
    public void signNormControl(Long doc_id, String login) {
        Engineer engineer = engineerService.findByLogin(login)
                .orElseThrow(() -> ExceptionsProducer.throwNotFoundByNameException(Engineer.class, login));
        DocumentApprovals documentApprovals = findEntityById(doc_id).getDocumentApprovals();
        if (!Objects.isNull(documentApprovals)) {
            documentApprovals.setNormControl(engineer);
            documentApprovals.setNormControlSign(LocalDate.now());
        }
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
