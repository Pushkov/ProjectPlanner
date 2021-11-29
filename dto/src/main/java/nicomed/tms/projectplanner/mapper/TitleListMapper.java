package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.TitleListDto;
import nicomed.tms.projectplanner.entity.TitleList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {TechnicalTaskMapper.class, MemoMapper.class})
public interface TitleListMapper extends DateTimeMapper {

    @Mapping(target = "technicalTaskDto", source = "titleList.technicalTasks")
    @Mapping(target = "memoDto", source = "titleList.memos")
    TitleListDto mapToDto(TitleList titleList);

    TitleList mapToEntity(TitleListDto dto);


}
