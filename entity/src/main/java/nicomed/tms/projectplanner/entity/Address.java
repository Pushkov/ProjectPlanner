package nicomed.tms.projectplanner.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Address extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;
    private String mobile;
    private String email;
    private String office;

    @OneToOne(mappedBy = "address")
    private Engineer engineer;
}