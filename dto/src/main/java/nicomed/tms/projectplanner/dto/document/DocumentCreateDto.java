package nicomed.tms.projectplanner.dto.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nicomed.tms.projectplanner.dto.document.format.DocumentFormatDto;
import nicomed.tms.projectplanner.dto.project.ProjectForListDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@SuperBuilder
@Data
public class DocumentCreateDto {

    private String designation;
    private String name;
    private Integer qty;
    private List<DocumentFormatDto> documentFormatDto;
    private List<ProjectForListDto> projects;
    private DocumentApprovalsDto documentApprovalsDto;

}
