package nicomed.tms.projectplanner.dto.plan;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PlanPointDto {

    private Long id;
    private Integer orderInPlan;
    private Integer year;
    private Integer month;
    private Long departmentId;
    private String departmentName;
    private Long projectId;
    private String projectDesignation;
    private String projectName;
    private Long designerId;
    private String designerName;

    @NotNull(message = "date must by not null")
    @PastOrPresent(message = "it`s not date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @NotNull(message = "date must by not null")
    @PastOrPresent(message = "it`s not date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    private Integer readiness;

}
