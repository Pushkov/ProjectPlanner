package nicomed.tms.projectplanner.dto.workshop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class WorkshopDto {
    private Long id;
    private String name;
    private String fullName;
    private int index;
    private String departmentName;

}
