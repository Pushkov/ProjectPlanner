package nicomed.tms.projectplanner.config;

import nicomed.tms.projectplanner.security.CustomPasswordEncoderFactories;
import nicomed.tms.projectplanner.security.JpaUserDetailsService;
import nicomed.tms.projectplanner.security.RestHeaderAuthFilter;
import nicomed.tms.projectplanner.security.RestJwtTokenAuthFilter;
import nicomed.tms.projectplanner.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    PasswordEncoder passwordEncoder() {
        return CustomPasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    AuthenticationManager getAuthenticationManager() throws Exception {
        return this.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
//                .httpBasic().disable()
                .csrf().ignoringAntMatchers("/planner/api/**")
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(restHeaderAuthFilter(authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(tokenAuthFilter(authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
        ;
        //region ------- PERMIT ALL
//        http.authorizeRequests(req -> req
//                .antMatchers("**").permitAll()
//        )
        // endregion

        http.authorizeRequests(req -> req
                .antMatchers(HttpMethod.GET, "/resources/**", "/webjars/**", "/static/**", "/css/**", "/js/**").permitAll()
                .antMatchers(HttpMethod.POST, "/planner/api/v1/auth/login", "/planner/api/v1/auth/login").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/planner/api/v1/documents/{id}/verifier/{designer}").hasAuthority("DOC_VERIF")
        )
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin(loginConf -> {
                    loginConf.loginProcessingUrl("/planner/auth/login")
                            .loginPage("/").permitAll()
                            .successForwardUrl("/planner/index")
                            .passwordParameter("password")
                            .usernameParameter("login")
                            .defaultSuccessUrl("/")
                            .failureUrl("/?error");
//                })
//                .formLogin(loginConf -> {
//                    loginConf.loginProcessingUrl("/planner/auth/login")
//                            .loginPage("/").permitAll()
//                            .successForwardUrl("/")
//                            .defaultSuccessUrl("/")
//                            .failureUrl("/?error");
                });
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

    public RestJwtTokenAuthFilter tokenAuthFilter(AuthenticationManager authenticationManager) {
        RestJwtTokenAuthFilter filter = new RestJwtTokenAuthFilter(new AntPathRequestMatcher("/planner/api/**"), jwtTokenProvider);
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    public RestHeaderAuthFilter restHeaderAuthFilter(AuthenticationManager authenticationManager) {
        RestHeaderAuthFilter filter = new RestHeaderAuthFilter(new AntPathRequestMatcher("/planner/api/**"));
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }
}
