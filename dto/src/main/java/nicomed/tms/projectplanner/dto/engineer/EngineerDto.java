package nicomed.tms.projectplanner.dto.engineer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nicomed.tms.projectplanner.dto.AddressDto;

import javax.validation.Valid;
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
    private Long roleId;
    private String roleName;
    @NotNull
    private Long departmentId;
    private String departmentName;

    private String status;
    @NotNull
    @Valid
    private AddressDto contactDetails;
}
