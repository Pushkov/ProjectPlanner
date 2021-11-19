package nicomed.tms.projectplanner.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProjectDtoShort {

    private Long id;
    private String designation;
    private String name;

}
