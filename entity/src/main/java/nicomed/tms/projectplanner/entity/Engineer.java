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

    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "PATRONYMIC_NAME")
    private String patronymicName;
    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;
    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;

}