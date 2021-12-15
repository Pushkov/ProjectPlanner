package nicomed.tms.projectplanner.qualifier;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.workshop.WorkshopDto;
import nicomed.tms.projectplanner.dto.workshop.WorkshopSimpleDto;
import nicomed.tms.projectplanner.entity.Workshop;
import nicomed.tms.projectplanner.repository.WorkshopRepository;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class WorkshopQualifier {

    private final WorkshopRepository workshopRepository;

    public Workshop getBySimple(WorkshopSimpleDto dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        return getWorkshop(dto.getId());
    }

    public Workshop getBy(WorkshopDto dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        return getWorkshop(dto.getId());
    }

    private Workshop getWorkshop(Long id) {
        if (id == null) {
            return null;
        }
        return workshopRepository.findById(id).orElseThrow(() -> new NoSuchElementException(""));
    }
}
