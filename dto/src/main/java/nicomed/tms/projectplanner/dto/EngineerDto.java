package nicomed.tms.projectplanner.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
public class EngineerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymicName;
    private String roleName;
    private String departmentName;
    private String status;
    private AddressDto contactDetails;

}

/*
                <xsd:element name="id" type="xsd:unsignedLong"/>
                <xsd:element name="firstName" type="xsd:string"/>
                <xsd:element name="lastName" type="xsd:string"/>
                <xsd:element name="patronymicName" type="xsd:string"/>
                <xsd:element name="roleName" type="xsd:string"/>
                <xsd:element name="departmentName" type="xsd:string"/>
                <xsd:element name="status" type="xsd:string"/>
                <xsd:element ref="Contact_details"/>
 */