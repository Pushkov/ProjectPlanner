package nicomed.tms.projectplanner.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "PERSONAL_SETTINGS")
public class PersonalSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LOCALE")
    private String locale;

    @OneToOne(mappedBy = "settings")
    private Engineer engineer;

}
