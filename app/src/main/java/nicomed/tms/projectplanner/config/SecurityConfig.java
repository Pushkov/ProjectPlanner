package nicomed.tms.projectplanner.config;

import nicomed.tms.projectplanner.security.CustomPasswordEncoderFactories;
import nicomed.tms.projectplanner.security.JpaUserDetailsService;
import nicomed.tms.projectplanner.security.RestHeaderAuthFilter;
import nicomed.tms.projectplanner.security.RestPathAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JpaUserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return CustomPasswordEncoderFactories.createDelegatingPasswordEncoder();
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
        )

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

    //region ------- InMemory Auth
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("pushkov").password("{noop}pan").roles("ENGINEER")
//                .and()
//                .withUser("kupreev").password("{noop}kmp").roles("MN_DEPT");
//    }
    // endregion

    public RestHeaderAuthFilter restHeaderAuthFilter(AuthenticationManager authenticationManager) {
        RestHeaderAuthFilter filter = new RestHeaderAuthFilter(new AntPathRequestMatcher("/planner/api/**"));
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    public RestPathAuthFilter restPathAuthFilter(AuthenticationManager authenticationManager) {
        RestPathAuthFilter filter = new RestPathAuthFilter(new AntPathRequestMatcher("/planner/api/**"));
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }


}
