package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.document.format.FormatDto;
import nicomed.tms.projectplanner.entity.SheetFormat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface FormatMapper {

    @Mapping(target = "name", source = "format.format")
    @Mapping(target = "size", source = "size")
    FormatDto mapToDto(SheetFormat format);
}
