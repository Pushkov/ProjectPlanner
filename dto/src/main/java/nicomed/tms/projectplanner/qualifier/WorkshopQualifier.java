package nicomed.tms.projectplanner.qualifier;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.workshop.WorkshopDto;
import nicomed.tms.projectplanner.dto.workshop.WorkshopSimpleDto;
import nicomed.tms.projectplanner.entity.Workshop;
import nicomed.tms.projectplanner.repository.WorkshopRepository;
import org.mapstruct.Named;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WorkshopQualifier extends AbstractEntityQualifier<Workshop, Long> {

    private final WorkshopRepository workshopRepository;

    @Override
    public JpaRepository<Workshop, Long> getRepository() {
        return workshopRepository;
    }

    public Workshop getBySimple(WorkshopSimpleDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }

    public Workshop getBy(WorkshopDto dto) {
        return dto != null ? getEntity(dto.getId()) : null;
    }

    @Named("workshopId")
    public Workshop getById(Long id) {
        return id != null ? getEntity(id) : null;
    }

}
