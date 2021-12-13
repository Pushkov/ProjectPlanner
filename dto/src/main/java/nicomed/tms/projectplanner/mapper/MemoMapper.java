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
@Mapper(componentModel = "spring", uses = {ProjectMapper.class, TitleListMapper.class, WorkshopMapper.class})
public interface MemoMapper {

    @Named("MemoForList")
    MemoForListDto mapToSimpleDto(Memo memo);

    @Mapping(target = "projects", source = "memo.projects")
    @Mapping(target = "titleListYear", source = "memo.titleList.id")
    @Mapping(target = "workshopId", source = "memo.workshop.id")
    @Mapping(target = "workshopName", source = "memo.workshop.name")
    MemoDto mapToDto(Memo memo);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "titleList", ignore = true)
    @Mapping(target = "workshop", ignore = true)
    Memo mapToEntity(MemoDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "titleList", ignore = true)
    @Mapping(target = "workshop", ignore = true)
    void mapToEntity(@MappingTarget Memo memo, MemoDto dto);

}
