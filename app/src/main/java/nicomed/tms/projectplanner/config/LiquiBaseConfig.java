package nicomed.tms.projectplanner.config;

import liquibase.integration.spring.SpringLiquibase;

//@Configuration
public class LiquiBaseConfig {

    //    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase-changeLog.xml");
        return liquibase;
    }
}
