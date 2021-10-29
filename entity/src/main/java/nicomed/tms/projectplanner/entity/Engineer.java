package nicomed.tms.projectplanner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nicomed.tms.projectplanner.enums.Status;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Engineer extends BaseEntity<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String patronymicName;
    @ManyToOne
    private Role role;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;
    @ManyToOne
    private Department department;
    @Enumerated(EnumType.STRING)
    private Status status;

}