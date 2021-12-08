package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.document.DocumentApprovalsDto;
import nicomed.tms.projectplanner.dto.document.DocumentCreateDto;
import nicomed.tms.projectplanner.dto.document.DocumentSignedDto;
import nicomed.tms.projectplanner.dto.document.DocumentSimpleDto;
import nicomed.tms.projectplanner.dto.document.format.DocumentFormatDto;
import nicomed.tms.projectplanner.entity.Document;
import nicomed.tms.projectplanner.entity.DocumentApprovals;
import nicomed.tms.projectplanner.entity.DocumentFormat;
import nicomed.tms.projectplanner.entity.DocumentSigned;
import nicomed.tms.projectplanner.mapper.DocumentApprovalsMapper;
import nicomed.tms.projectplanner.mapper.DocumentFormatMapper;
import nicomed.tms.projectplanner.mapper.DocumentSignedMapper;
import nicomed.tms.projectplanner.repository.DocumentSignedRepository;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import nicomed.tms.projectplanner.repository.specification.SearcheableService;
import nicomed.tms.projectplanner.services.DocumentSignedService;
import nicomed.tms.projectplanner.services.EngineerService;
import nicomed.tms.projectplanner.services.SheetFormatService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
        List<DocumentFormatDto> formatDtos = dto.getDocumentFormatDto();
        document.setDocumentFormats(getDocumentFormats(document, formatDtos));
        document.setDocumentApprovals(getDocumentApprovals(dto.getDocumentApprovalsDto()));
        documentSignedRepository.save(document);
    }

    @Transactional
    @Override
    public void save(Long id, DocumentCreateDto dto) {
        DocumentSigned document = findEntityById(id);
        mapper.mapToEntity(document, dto);
        List<DocumentFormatDto> formatDtos = dto.getDocumentFormatDto();
        document.setDocumentFormats(getDocumentFormats(document, formatDtos));
        document.setDocumentApprovals(getDocumentApprovals(dto.getDocumentApprovalsDto()));
        documentSignedRepository.save(document);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected List<DocumentFormat> getDocumentFormats(Document document, List<DocumentFormatDto> formatDtos) {
        List<DocumentFormat> formats = new ArrayList<>();
        for (DocumentFormatDto formatDto : formatDtos) {
            DocumentFormat documentFormat = formatMapper.mapToEntity(formatDto);
            documentFormat.setDocument(document);
            documentFormat.setFormat(
                    sheetFormatService.findEntityByFormatName(formatDto.getFormatDto().getName())
            );
            formats.add(documentFormat);
        }
        return formats;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected DocumentApprovals getDocumentApprovals(DocumentApprovalsDto dto) {
        // todo document approvals
        System.out.println(" approvals designer " + dto.getDesignerName());
        DocumentApprovals approvals = approvalsMapper.mapToEntity(dto);
        approvals.setDesigner(engineerService.findEntityById(1L));
        return approvals;
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
