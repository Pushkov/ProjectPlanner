package nicomed.tms.projectplanner.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EngineerJavaDto {

    private String firstName;
    private String lastName;
    private String patronymicName;
    private AddressJavaDto address;
    private String roleName;
}
