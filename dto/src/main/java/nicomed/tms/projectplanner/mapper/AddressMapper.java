package nicomed.tms.projectplanner.mapper;

import nicomed.tms.projectplanner.dto.AddressJavaDto;
import nicomed.tms.projectplanner.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressJavaDto mapToJavaDto(Address address);

}
