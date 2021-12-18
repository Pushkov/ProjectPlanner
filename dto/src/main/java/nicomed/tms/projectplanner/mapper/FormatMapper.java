package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.document.format.FormatDto;
import nicomed.tms.projectplanner.entity.SheetFormat;
import nicomed.tms.projectplanner.qualifier.FormatQualifier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {FormatQualifier.class})
public interface FormatMapper {

    @Mapping(target = "name", source = "format.format")
    @Mapping(target = "size", source = "size")
    FormatDto mapToDto(SheetFormat format);

}
