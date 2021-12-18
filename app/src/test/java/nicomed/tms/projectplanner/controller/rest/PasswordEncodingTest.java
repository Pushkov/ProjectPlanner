package nicomed.tms.projectplanner.controller.rest;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordEncodingTest {

    public static final String PASSWORD = "password";
    public static final String PWD_PUSHKOV = "pan";
    public static final String PWD_LAPTEV = "lsv";
    public static final String PWD_KUPREEV = "kmp";
    public static final String PWD_MELNIKOV = "mvv";
    public static final String PWD_MULUTINA = "mlr";
    public static final String PWD_SOROKOVIKOVA = "sve";
    public static final String PWD_NAGORNY = "nam";

    @Test
    void testbcrypt() {
        PasswordEncoder bcrypt = new BCryptPasswordEncoder(9);
        System.out.println("Пушков");
        System.out.println(bcrypt.encode(PWD_PUSHKOV));
        System.out.println("Лаптев");
        System.out.println(bcrypt.encode(PWD_LAPTEV));
        System.out.println("Купреев");
        System.out.println(bcrypt.encode(PWD_KUPREEV));
        System.out.println("Мельников");
        System.out.println(bcrypt.encode(PWD_MELNIKOV));
        System.out.println("Милютина");
        System.out.println(bcrypt.encode(PWD_MULUTINA));
        System.out.println("Сороковикова");
        System.out.println(bcrypt.encode(PWD_SOROKOVIKOVA));
        System.out.println("Нагорный");
        System.out.println(bcrypt.encode(PWD_NAGORNY));

//        System.out.println(bcrypt.encode("eng"));
//        System.out.println(bcrypt.encode("nmd"));
//        System.out.println(bcrypt.encode("nko"));
//        System.out.println(bcrypt.encode("zmn"));
//        System.out.println(bcrypt.encode("mn"));
//        System.out.println(bcrypt.encode("adm"));


    }

    @Test
    void testSha256() {
        PasswordEncoder sha256 = new StandardPasswordEncoder();
        System.out.println(sha256.encode(PASSWORD));
        System.out.println(sha256.encode(PASSWORD));

        System.out.println(sha256.encode("1111"));
        System.out.println(sha256.encode("2222"));
        System.out.println(sha256.encode("1234"));
    }

    @Test
    void testLdap() {
        PasswordEncoder ldap = new LdapShaPasswordEncoder();
        System.out.println(ldap.encode(PASSWORD));
        System.out.println(ldap.encode(PASSWORD));

        String encodedPwd = ldap.encode(PASSWORD);
        assertTrue(ldap.matches(PASSWORD, encodedPwd));
    }

    @Test
    void testNoOp() {
        PasswordEncoder noOp = NoOpPasswordEncoder.getInstance();

        System.out.println("noop= " + noOp.encode(PASSWORD));
    }

}
