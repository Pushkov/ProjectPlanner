package nicomed.tms.projectplanner.dto;

import nicomed.tms.projectplanner.entity.Department;
//import org.mapstruct.Mapper;
//import org.mapstruct.factory.Mappers;

//@Mapper
public interface DepartmentMapper {

//    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    Department mapToEntity(DepartmentDTO dto);

    DepartmentDTO mapToDto(Department department);

    /*
        UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "emailAddress", source = "dto.email")
    UserEntity mapToEntity(UserDto dto);

    @AfterMapping
    default void fullName(@MappingTarget UserEntity userEntity) {
        String fullName = NameUtil.getFullName(userEntity.getFirstName(), userEntity.getLastName());
        userEntity.setFullName(fullName);
    }


     */
}
