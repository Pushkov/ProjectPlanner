package nicomed.tms.projectplanner.mapper;


import nicomed.tms.projectplanner.dto.MemoDto;
import nicomed.tms.projectplanner.entity.Memo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {ProjectMapper.class})
public interface MemoMapper extends DateTimeMapper {

    MemoDto mapToDto(Memo memo);

    Memo mapToEntity(MemoDto dto);

}
