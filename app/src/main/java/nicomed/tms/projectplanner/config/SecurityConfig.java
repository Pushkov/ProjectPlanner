package nicomed.tms.projectplanner.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests(req -> req
                .antMatchers("/", "/login", "/planner", "/planner/index", "/resources/**", "/webjars/**").permitAll()
                .antMatchers(HttpMethod.GET, "/planner/overview").authenticated()
                .mvcMatchers(HttpMethod.GET, "/owners/plans").authenticated())

                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin();
////                .formLogin(loginConf -> {
////                    loginConf.loginProcessingUrl("/login")
////                            .loginPage("/").permitAll()
////                            .successForwardUrl("/")
////                            .passwordParameter("pwd")
////                            .usernameParameter("usr")
////                            .defaultSuccessUrl("/")
////                            .failureUrl("/?error");
////                });
////                .logout(logoutConf -> {
////                    logoutConf.logoutSuccessUrl("/?logout").permitAll()
////                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"));
////                })
////                .rememberMe()
////                .tokenRepository(persistentTokenRepository)
////                .userDetailsService(userDetailsService);
//
    }
}
