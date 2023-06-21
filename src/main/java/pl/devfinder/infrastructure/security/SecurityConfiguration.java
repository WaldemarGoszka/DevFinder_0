package pl.devfinder.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private UserDetailsService userDetailsService;

    // z filmu
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserSecurityServiceConfig();
//    }

    @Bean
    public  static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService());
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }


//    @Bean
//    public AuthenticationManager authManager(
//            HttpSecurity http,
//            PasswordEncoder passwordEncoder,
//            UserDetailsService userDetailService
//    )
//            throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailService)
//                .passwordEncoder(passwordEncoder)
//                .and()
//                .build();
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests((authorize) ->
//                        authorize.requestMatchers("/register/**").permitAll()
//                                .requestMatchers("/index").permitAll()
//                                .requestMatchers("/users").hasRole("ADMIN")
//                ).formLogin(
//                        form -> form
//                                .loginPage("/login")
//                                .loginProcessingUrl("/login")
//                                .defaultSuccessUrl("/users")
//                                .permitAll()
//                ).logout(
//                        logout -> logout
//                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                                .permitAll()
//                );
//        return http.build();
//    }


    @Bean
    @ConditionalOnProperty(value = "spring.security.enabled", havingValue = "true", matchIfMissing = true)
    SecurityFilterChain securityEnabled(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/login", "/error", "/images/error.png", "/*").permitAll()
//            .requestMatchers("/mechanic/**").hasAnyAuthority("MECHANIC")
//            .requestMatchers("/salesman/**", "/purchase/**", "/service/**").hasAnyAuthority("SALESMAN")
//            .requestMatchers("/", "/car/**", "/images/**").hasAnyAuthority("MECHANIC", "SALESMAN")
//            .requestMatchers("/api/**").hasAnyAuthority("REST_API")
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/candidate/portal")
                        .permitAll())

                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    @ConditionalOnProperty(value = "spring.security.enabled", havingValue = "false")
    SecurityFilterChain securityDisabled(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests()
                .anyRequest()
                .permitAll();

        return http.build();
    }

}
