package nicomed.tms.projectplanner.services.jpa;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.document.DocumentCreateDto;
import nicomed.tms.projectplanner.dto.document.DocumentDto;
import nicomed.tms.projectplanner.dto.document.DocumentSignedDto;
import nicomed.tms.projectplanner.dto.document.DocumentSimpleDto;
import nicomed.tms.projectplanner.dto.document.format.DocumentFormatDto;
import nicomed.tms.projectplanner.entity.Document;
import nicomed.tms.projectplanner.entity.DocumentFormat;
import nicomed.tms.projectplanner.entity.DocumentSigned;
import nicomed.tms.projectplanner.mapper.DocumentFormatMapper;
import nicomed.tms.projectplanner.mapper.DocumentMapper;
import nicomed.tms.projectplanner.mapper.DocumentSignedMapper;
import nicomed.tms.projectplanner.repository.DocumentFormatRepository;
import nicomed.tms.projectplanner.repository.DocumentRepository;
import nicomed.tms.projectplanner.repository.specification.SearchableRepository;
import nicomed.tms.projectplanner.repository.specification.SearcheableService;
import nicomed.tms.projectplanner.repository.specification.filter.DocumentFilter;
import nicomed.tms.projectplanner.services.DocumentFormatService;
import nicomed.tms.projectplanner.services.DocumentService;
import nicomed.tms.projectplanner.services.SheetFormatService;
import nicomed.tms.projectplanner.services.config.JpaImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static nicomed.tms.projectplanner.repository.specification.DocumentSpecification.findByTerm;
import static nicomed.tms.projectplanner.services.exception.ExceptionsProducer.trowIncorrectPageAssignmentException;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@JpaImpl
public class DocumentJpaServiceImpl extends AbstractDoubleDtoJpaService<DocumentDto, DocumentSimpleDto, Document, Long>
        implements DocumentService, SearcheableService<Document> {

    private final DocumentRepository documentRepository;
    private final DocumentFormatRepository documentFormatRepository;
    private final DocumentFormatService documentFormatService;
    private final SheetFormatService sheetFormatService;
    private final DocumentMapper mapper;
    private final DocumentSignedMapper signedMapper;
    private final DocumentFormatMapper formatMapper;

    @Override
    public JpaRepository<Document, Long> getRepository() {
        return documentRepository;
    }

    @Override
    public SearchableRepository<Document, ?> getSearchRepository() {
        return documentRepository;
    }

    @Override
    public DocumentSignedDto findById(Long id) {
        Document document = findEntityById(id);
        if (document instanceof DocumentSigned) {
            return signedMapper.mapToDto((DocumentSigned) document);
        }
        return mapper.mapToDto(document);
    }

    @Override
    public Long count() {
        return documentRepository.count();
    }

    @Override
    public Page<DocumentSimpleDto> findPage(Integer page, Integer offset) {
        if (page >= 0 && offset > 0) {
            PageRequest pageRequest = PageRequest.of(page, offset);
            List<DocumentSimpleDto> documentSimpleDtos = documentRepository.findAll(pageRequest).stream()
                    .map(this::mapToSimpleDto)
                    .collect(Collectors.toList());
            return new PageImpl<>(documentSimpleDtos);
        }
        throw trowIncorrectPageAssignmentException("Incorrect page assignment");
    }

    @Override
    public List<DocumentSimpleDto> search(DocumentFilter engineerFilter) {
        Specification<Document> specification = findByTerm(engineerFilter.getTerm());
        return documentRepository.findAll(specification).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DocumentSimpleDto mapToSimpleDto(Document entity) {
        return mapper.mapToSimpleDto(entity);
    }

    @Override
    public DocumentDto mapToDto(Document entity) {
        return mapper.mapToDto(entity);
    }

    @Override
    public Document mapToEntity(DocumentDto dto) {
        return mapToEntity(dto);
    }

    @Override
    public Class<Document> getEntityClass() {
        return Document.class;
    }

    @Transactional
    @Override
    public void save(DocumentCreateDto dto) {
        Document document = mapper.mapToEntity(dto);
        List<DocumentFormatDto> formatDtos = dto.getDocumentFormatDto();
        document.setDocumentFormats(getDocumentFormats(document, formatDtos));
        documentRepository.save(document);
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

    @Override
    public void save(Long id, DocumentCreateDto dto) {
        Document document = findEntityById(id);
        List<DocumentFormatDto> formatDtos = dto.getDocumentFormatDto();
        document.setDocumentFormats(getDocumentFormats(document, formatDtos));
        documentRepository.save(document);
    }
}
