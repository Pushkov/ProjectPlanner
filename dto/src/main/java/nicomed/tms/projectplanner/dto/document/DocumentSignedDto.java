package nicomed.tms.projectplanner.dto.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nicomed.tms.projectplanner.dto.DocumentApprovalsDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@SuperBuilder
@Data
public class DocumentSignedDto extends DocumentDto {

    //    private Long id;
//    private String designation;
//    private String name;
    private DocumentApprovalsDto documentApprovalsDto;
    private List<DocumentFormatDto> documentFormatDto;
}
