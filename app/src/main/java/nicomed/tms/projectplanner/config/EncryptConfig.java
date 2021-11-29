package nicomed.tms.projectplanner.config;

import com.ulisesbocchio.jasyptspringboot.encryptor.SimplePBEByteEncryptor;
import com.ulisesbocchio.jasyptspringboot.encryptor.SimplePBEStringEncryptor;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PBEByteEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.salt.SaltGenerator;
import org.jasypt.salt.StringFixedSaltGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class EncryptConfig {

    @Value("${jasypt.encryptor.password:}")
    private String password;

    @Value("${jasypt.encryptor.algorithm:PBEWithMD5AndDES}")
    private String algorithm;

    @Value("${jasypt.encryptor.key-obtention-iterations:1}")
    private int iterations;

    @Value("${jasypt.salt:salt}")
    private String salt;

    @Value("${jasypt.encryptor.pool-size:1}")
    private int poolSize;

    @Profile("!prod")
    @Bean("customEncryptor")
    public StringEncryptor stringEncryptor(PBEByteEncryptor delegate) {
        return new SimplePBEStringEncryptor(delegate);
    }

    @Bean
    public PBEByteEncryptor pbeByteEncryptor(SaltGenerator saltGenerator) {
        SimplePBEByteEncryptor encryptor = new SimplePBEByteEncryptor();
        encryptor.setPassword(password);
        encryptor.setAlgorithm(algorithm);
        encryptor.setIterations(iterations);
        encryptor.setSaltGenerator(saltGenerator);
        return encryptor;
    }

    @Profile("prod")
    @Bean("polledEncryptor")
    public StringEncryptor polledEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm(algorithm);
        config.setPoolSize(poolSize);
        config.setSaltGenerator(saltGenerator());
        config.setKeyObtentionIterations(iterations);
        encryptor.setConfig(config);
        return encryptor;
    }

    @Bean
    public SaltGenerator saltGenerator() {
        return new StringFixedSaltGenerator(salt);
    }
}

