package nicomed.tms.projectplanner.dto.role;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nicomed.tms.projectplanner.dto.PermissionDto;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@SuperBuilder
@Data
public class RoleDto extends RoleSimpleDto {

    List<PermissionDto> permissions;
}
