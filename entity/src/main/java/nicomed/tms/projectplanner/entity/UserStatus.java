package nicomed.tms.projectplanner.entity;

import lombok.*;
import nicomed.tms.projectplanner.enums.Status;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class UserStatus {
    @Id
    @Enumerated(EnumType.STRING)
    private Status status;

}