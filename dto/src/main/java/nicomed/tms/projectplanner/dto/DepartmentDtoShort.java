package nicomed.tms.projectplanner.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DepartmentDtoShort {

    private String name;
    private String fullName;
}
