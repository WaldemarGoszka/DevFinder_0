//package pl.devfinder.infrastructure.security.configuration;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import pl.devfinder.business.management.Keys;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfiguration {
//    private final UserDetailsServiceCustom userDetailsServiceCustom;
//
//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
////    @Bean
////    public UserDetailsService userDetailsService() {
////        return new UserDetailsServiceCustom();
////    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsServiceCustom);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        AuthenticationManagerBuilder authenticationManagerBuilder
////                = http.getSharedObject(AuthenticationManagerBuilder.class);
////
////        authenticationManagerBuilder
////                .userDetailsService(userDetailsService())
////                .passwordEncoder(passwordEncoder());
////
////        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
//
//        return http
//                .csrf().disable()
//                .authorizeHttpRequests()
////                .requestMatchers("/login", "/error", "/*", "/js/**", "/css/**", "/lib/**", "/scss/**", "/images/**", "/img/**").permitAll()
//                .requestMatchers("/candidate/**").hasAuthority(Keys.Role.CANDIDATE.getName())
//                .requestMatchers("/employer/**").hasAuthority(Keys.Role.EMPLOYER.getName())
//                .anyRequest().authenticated()
//                .and()
//
//                // ... pozostała konfiguracja ...
////                .formLogin()
////                .loginPage("/login")
////                .loginProcessingUrl("/do-login")
////                .successHandler(customAuthenticationSuccessHandler())
////                .permitAll()
//                // ... pozostała konfiguracja ...
//
//                .formLogin()
//                .loginPage("/login")
//                //.usernameParameter("email")
//                //.loginProcessingUrl("/do-login")
//                .defaultSuccessUrl("/", true)
//                //.failureForwardUrl("/login?error")
//                .failureUrl("/login?error")
//                .permitAll()
//                .and()
//                .logout()
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .deleteCookies("JSESSIONID")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login?logout")
//                .permitAll()
//                .and()
//
////                .exceptionHandling()
////                .accessDeniedPage("/403")
////                .and()
//
////                .csrf().disable()
//
//                //.authenticationManager(authenticationManager)
//
//                //.sessionManagement()
//                //.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//
//        .build();
//    }
//
////    @Bean
////    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
////        return new CustomAuthenticationSuccessHandler();
////    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails employer = User
//                .withUsername("employer")
//                .password(passwordEncoder().encode("12345678"))
//                .roles(Keys.Role.EMPLOYER.getName())
//                .build();
//        UserDetails candidate = User
//                .withUsername("candidate")
//                .password(passwordEncoder().encode("12345678"))
//                .roles(Keys.Role.CANDIDATE.getName())
//                .build();
//        return new InMemoryUserDetailsManager(employer, candidate);
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) ->
//                web.ignoring()
////                        .requestMatchers("/js/**", "/css/**", "/lib/**", "/scss/**", "/images/**");
//                        .requestMatchers("/*", "/js/**", "/css/**", "/lib/**", "/scss/**", "/images/**", "/img/**",
//                                "/register/**", "/login/**", "/login");
//    }
//
////    @Bean
////    @ConditionalOnProperty(value = "spring.security.enabled", havingValue = "false")
////    SecurityFilterChain securityDisabled(HttpSecurity http) throws Exception {
////        http.csrf()
////                .disable()
////                .authorizeHttpRequests()
////                .anyRequest()
////                .permitAll();
////
////        return http.build();
////    }
//
//}
package pl.devfinder.infrastructure.security.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.devfinder.business.management.Keys;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsServiceCustom userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/",
                        "/login",
                        "/error",
                        "/register/**",
                        "/js/**",
                        "/css/**",
                        "/lib/**",
                        "/scss/**",
                        "/images/**",
                        "/img/**")
                .permitAll()
                .requestMatchers("/candidate/**").hasAuthority(Keys.Role.CANDIDATE.getName())
                .requestMatchers("/employer/**").hasAuthority(Keys.Role.EMPLOYER.getName())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .permitAll().and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .and()
                .build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) ->
//                web.ignoring()
////                        .requestMatchers("/js/**", "/css/**", "/lib/**", "/scss/**", "/images/**");
//                        .requestMatchers("/*", "/js/**", "/css/**", "/lib/**", "/scss/**", "/images/**", "/img/**",
//                                "/register/**", "/login/**", "/login");
//    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails employer = User
                .withUsername("employer")
                .password(passwordEncoder().encode("12345678"))
                .roles(Keys.Role.EMPLOYER.getName())
                .build();
        UserDetails candidate = User
                .withUsername("candidate")
                .password(passwordEncoder().encode("12345678"))
                .roles(Keys.Role.CANDIDATE.getName())
                .build();
        return new InMemoryUserDetailsManager(employer, candidate);
    }
}
