package nicomed.tms.projectplanner.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    @Value("${spring.datasource.password:undef}")
    private String pwd;

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

    }
}
