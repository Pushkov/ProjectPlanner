package nicomed.tms.projectplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"nicomed.tms.listener", "nicomed.tms.projectplanner"})
@SpringBootApplication
public class ProjectPlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectPlannerApplication.class, args);
    }

}
