package nicomed.tms.projectplanner.qualifier;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.SheetFormat;
import nicomed.tms.projectplanner.enums.Format;
import nicomed.tms.projectplanner.repository.SheetFormatRepository;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Named;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FormatQualifier extends AbstractEntityQualifier<SheetFormat, Long> {

    private final SheetFormatRepository repository;

    @Override
    public JpaRepository<SheetFormat, Long> getRepository() {
        return repository;
    }

//    @Named("formatBy")
//    public SheetFormat getBy(FormatDto dto) {
//        return dto != null ? getEntity(dto.getId()) : null;
//    }

    @Named("formatByName")
    public SheetFormat getBy(String name) {
//        if (dto == null) {
//            return null;
//        }
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        Format format = Format.getByName(name);
        return repository.findByFormat(format);
    }
}

