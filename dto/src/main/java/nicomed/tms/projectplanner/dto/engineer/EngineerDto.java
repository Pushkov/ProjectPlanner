package nicomed.tms.projectplanner.dto.engineer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nicomed.tms.projectplanner.dto.AddressDto;
import nicomed.tms.projectplanner.dto.department.DepartmentSimpleDto;
import nicomed.tms.projectplanner.dto.role.RoleSimpleDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
public class EngineerDto {
    private Long id;
    @NotBlank
    private String login;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String patronymicName;
    @NotNull
    private RoleSimpleDto roleSimpleDto;
    @NotNull
    private DepartmentSimpleDto departmentSimpleDto;
    private String status;
    private AddressDto contactDetails;
}
