package nicomed.tms.projectplanner.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nicomed.tms.projectplanner.dto.memo.MemoDto;
import nicomed.tms.projectplanner.dto.task.TechnicalTaskDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
public class TitleListDto {

    private int id;
    private List<TechnicalTaskDto> technicalTaskDto;
    private List<MemoDto> memoDto;
}
