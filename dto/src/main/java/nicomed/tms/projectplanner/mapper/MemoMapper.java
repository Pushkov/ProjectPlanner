package nicomed.tms.projectplanner.mapper;


import nicomed.tms.projectplanner.dto.memo.MemoDto;
import nicomed.tms.projectplanner.dto.memo.MemoForListDto;
import nicomed.tms.projectplanner.entity.Memo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {ProjectMapper.class})
public interface MemoMapper extends DateTimeMapper {

    @Named("MemoForList")
    MemoForListDto mapToSimpleDto(Memo memo);

    @Mapping(target = "projects", source = "memo.projects")
    MemoDto mapToDto(Memo memo);

    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "id", ignore = true)
    Memo mapToEntity(MemoDto dto);

    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "id", ignore = true)
    void mapToEntity(@MappingTarget Memo memo, MemoDto dto);

}
