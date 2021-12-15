package nicomed.tms.projectplanner.qualifier;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.document.format.FormatDto;
import nicomed.tms.projectplanner.entity.SheetFormat;
import nicomed.tms.projectplanner.repository.SheetFormatRepository;
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

    @Named("formatQualif")
    public SheetFormat getBy(FormatDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }

}

