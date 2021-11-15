package nicomed.tms.projectplanner.mapper;


import nicomed.tms.projectplanner.dto.MemoDtoShort;
import nicomed.tms.projectplanner.entity.Memo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface MemoMapper extends DateTimeMapper {

    MemoDtoShort mapToDtoShort(Memo memo);

    List<MemoDtoShort> mapToListDtoShort(List<Memo> memoList);
}
