package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.planproject.PlanProjectDto;
import nicomed.tms.projectplanner.entity.PlanProject;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PlanProjectMapper {

    //    @Mapping(target = "month", expression = "java(integerToString(entity.getMonth()))")
    PlanProjectDto mapToDto(PlanProject entity);

    //    @Mapping(target = "month", expression = "java(stringToInteger(dto.getMonth()))")
    PlanProject mapToEntity(PlanProjectDto dto);

//    default String integerToString(Integer month) {
//        return  Month.of(month).getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru"));
//    }
//
//    default Integer stringToInteger(String month) {
//        return Month.  .valueOf(month).getValue();
//    }
}
