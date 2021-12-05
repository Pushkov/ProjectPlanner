package nicomed.tms.projectplanner.dto.department;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nicomed.tms.projectplanner.dto.engineer.EngineerDto;
import nicomed.tms.projectplanner.dto.workshop.WorkshopSimpleDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@SuperBuilder
@Data
public class DepartmentDto extends DepartmentSimpleDto {

    private Long basicDepId;
    private String basicDep;

    private List<EngineerDto> engineers;
    private List<WorkshopSimpleDto> workshops;

}
