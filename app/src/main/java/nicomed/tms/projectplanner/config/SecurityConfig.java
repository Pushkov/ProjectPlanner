package nicomed.tms.projectplanner.config;

import nicomed.tms.projectplanner.security.RestHeaderAuthFilter;
import nicomed.tms.projectplanner.security.RestPathAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(restHeaderAuthFilter(authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(restPathAuthFilter(authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .csrf().ignoringAntMatchers("/planner/api/**");


        http.authorizeRequests(req -> req
                .antMatchers("/", "/login", "/planner", "/planner/index", "/resources/**", "/webjars/**").permitAll()
                .antMatchers(HttpMethod.GET, "/planner/overview").authenticated())

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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("engineer").password("{noop}eng").roles("ENGINEER")
                .and()
                .withUser("mn_dep").password("{noop}mnd").roles("MN_DEPT")
                .and()
                .withUser("nk").password("{noop}nko").roles("N_CONTR")
                .and()
                .withUser("zmn").password("{noop}zmn").roles("ZMN")
                .and()
                .withUser("mn").password("{noop}mn").roles("MN")
                .and()
                .withUser("admin").password("{bcrypt}$2a$08$PyU48fZMRZfYc2hnxPTqMOKV84eAEL.Gg2w8ZdHmmWb1fyRtQHRby").roles("ADMIN");
    }

    public RestHeaderAuthFilter restHeaderAuthFilter(AuthenticationManager authenticationManager) {
        RestHeaderAuthFilter filter = new RestHeaderAuthFilter(new AntPathRequestMatcher("/planner/api/**"));
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    public RestPathAuthFilter restPathAuthFilter(AuthenticationManager authenticationManager) {
        RestPathAuthFilter filter = new RestPathAuthFilter(new AntPathRequestMatcher("/api/**"));
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }


}
