package nicomed.tms.projectplanner.mapper;


import nicomed.tms.projectplanner.dto.MemoDto;
import nicomed.tms.projectplanner.entity.Memo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface MemoMapper extends DateTimeMapper {

    MemoDto mapToDtoShort(Memo memo);

    List<MemoDto> mapToListDtoShort(List<Memo> memoList);
}
