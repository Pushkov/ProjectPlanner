package nicomed.tms.projectplanner.dto.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@SuperBuilder
@Data
public class DocumentSignedDto extends DocumentDto {

    @Builder.Default
    private DocumentApprovalsDto documentApprovalsDto = DocumentApprovalsDto.builder().build();
}
