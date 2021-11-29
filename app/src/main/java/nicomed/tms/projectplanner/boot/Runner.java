package nicomed.tms.projectplanner.boot;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Runner implements CommandLineRunner {

    @Value("${jpa.enable:undef}")
    private String test;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("run....");
        System.out.println("jpa enable: " + test);
    }
}