package nicomed.tms.projectplanner.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@EnableJpaRepositories(
        transactionManagerRef = "systemTransactionManager",
        entityManagerFactoryRef = "systemEntityManagerFactory",
        basePackages = {"nicomed.tms.system.repository"}
)
@Configuration
public class SystemDbConfig {

    @Bean("systemDataSource")
    @LiquibaseDataSource
    @ConfigurationProperties("spring.system-datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(MangedHikariDataSource.class).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean systemEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                             @Qualifier("systemDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("nicomed.tms.system.entity")
                .persistenceUnit("mainPersistenceUnit")
                .build();
    }

    @Bean
    public PlatformTransactionManager systemTransactionManager(@Qualifier("systemEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
