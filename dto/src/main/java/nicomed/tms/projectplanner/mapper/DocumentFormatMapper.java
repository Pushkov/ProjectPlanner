package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.document.format.DocumentFormatDto;
import nicomed.tms.projectplanner.entity.DocumentFormat;
import nicomed.tms.projectplanner.qualifier.FormatQualifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {FormatMapper.class, FormatQualifier.class})
public interface DocumentFormatMapper {

    @Mapping(target = "formatDto", source = "format")
    DocumentFormatDto mapToDto(DocumentFormat entity);

    @Mapping(target = "format", source = "dto.formatDto.name", qualifiedByName = "formatByName")
    DocumentFormat mapToEntity(DocumentFormatDto dto);

    @Mapping(target = "format", source = "dto.formatDto.name", qualifiedByName = "formatByName")
    void mapToEntity(@MappingTarget DocumentFormat format, DocumentFormatDto dto);
}
