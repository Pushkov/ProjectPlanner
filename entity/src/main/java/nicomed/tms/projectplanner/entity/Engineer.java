package nicomed.tms.projectplanner.entity;

import lombok.*;
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

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "patronymic_name")
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