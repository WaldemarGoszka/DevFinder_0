package pl.devfinder.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.devfinder.business.management.Keys;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceCustom();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder
                = http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());

        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        http
                .authorizeHttpRequests()
                .requestMatchers("/candidate/**").hasAuthority(Keys.Role.CANDIDATE.getName())
                .requestMatchers("/employer/**").hasAuthority(Keys.Role.EMPLOYER.getName())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/do-login")
                .defaultSuccessUrl("/index", true)
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()

                .exceptionHandling()
                .accessDeniedPage("/403")
                .and()

//                .csrf().disable()
                .authenticationManager(authenticationManager)

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)

        ;
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) ->
                web.ignoring()
                        .requestMatchers("/js/**", "/css/**", "/lib/**", "/scss/**", "/images/**");
    }
//    @Bean
//    @ConditionalOnProperty(value = "spring.security.enabled", havingValue = "false")
//    SecurityFilterChain securityDisabled(HttpSecurity http) throws Exception {
//        http.csrf()
//                .disable()
//                .authorizeHttpRequests()
//                .anyRequest()
//                .permitAll();
//
//        return http.build();
//    }

}
