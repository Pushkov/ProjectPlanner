package nicomed.tms.projectplanner.boot;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.Department;
import nicomed.tms.projectplanner.entity.Plan;
import nicomed.tms.projectplanner.entity.PlanPK;
import nicomed.tms.projectplanner.repository.DepartmentRepository;
import nicomed.tms.projectplanner.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
public class Runner implements CommandLineRunner {

    @Value("${spring.datasource.password:undef}")
    private String pwd;

    private final DepartmentRepository departmentRepository;
    private final PlanRepository planRepository;

//    @Qualifier("customEncryptor")
//    @Qualifier("polledEncryptor")
//    @Autowired
//    private StringEncryptor stringEncryptor;


    @Override
    public void run(String... args) throws Exception {

        System.out.println("run....");
        System.out.println("PWD: " + pwd);
//        String enc = stringEncryptor.encrypt(pwd);
//        System.out.println("e: " + enc);
//        System.out.println("d: " + stringEncryptor.decrypt(enc));
//
//        System.out.println("********************************");
//        System.out.println("encPWD: " + stringEncryptor.encrypt(pwd));
//        initDepartment();
//        initPlan();
    }

    private void initPlan() {
        Department department = departmentRepository.findByName("dep1")
                .orElseThrow(NoSuchElementException::new);

        Plan plan1 = Plan.builder()
                .id(PlanPK.builder()
                        .year(OffsetDateTime.now().getYear())
                        .month(OffsetDateTime.now().getMonthValue())
                        .department(department)
                        .build())
                .build();
        planRepository.save(plan1);
    }

    private void initDepartment() {
        Department dep1 = Department.builder()
                .name("dep1")
                .fullName("department 1")
                .build();

        departmentRepository.save(dep1);
    }
}
