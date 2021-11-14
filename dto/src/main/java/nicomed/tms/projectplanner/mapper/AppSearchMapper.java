package nicomed.tms.projectplanner.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {DocumentMapper.class, EngineerMapper.class})
public interface AppSearchMapper {

//    AppSearchWrapperDto map(List<Document> documents, List<Engineer> engineers);
}
