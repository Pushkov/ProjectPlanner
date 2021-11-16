package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.AddressDto;
import nicomed.tms.projectplanner.entity.Address;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AddressMapper {

    AddressDto mapToDto(Address address);
}
