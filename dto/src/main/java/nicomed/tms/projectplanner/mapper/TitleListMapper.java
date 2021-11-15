package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.TitleListDto;
import nicomed.tms.projectplanner.entity.TitleList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(uses = {TechnicalTaskMapper.class})
public interface TitleListMapper {

    @Mapping(target = "technicalTaskDtoShort", source = "titleList.technicalTasks")
    @Mapping(target = "memoDtoShort", source = "titleList.memos")
    TitleListDto mapToDto(TitleList titleList);

    List<TitleListDto> mapToListDto(List<TitleList> titleLists);

}
