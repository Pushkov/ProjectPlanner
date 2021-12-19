package nicomed.tms.projectplanner.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
public class AddressDto {

    @NotNull(message = "Phone must not empty")
    @Pattern(regexp = "\\d{2}-?\\d{2}", message = "phone incorrect")
    private String phone;

    @Size(min = 9, max = 9, message = "номер телефона должен состоять из 9 цифр")
    @Pattern(regexp = "\\d{2}-?\\d{3}-?\\d{2}-?\\d{2}", message = "phone incorrect")
    private String mobile;
    @Email
    private String email;
    private String office;
}
