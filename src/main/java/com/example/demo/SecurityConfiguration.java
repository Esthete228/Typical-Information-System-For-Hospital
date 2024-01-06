package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final DataSource dataSource;

    public SecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl userDetailsService = new JdbcDaoImpl();
        userDetailsService.setDataSource(dataSource);
        userDetailsService.setUsersByUsernameQuery("SELECT username, password, 'true' as enabled FROM doctor WHERE username = ?");
        userDetailsService.setAuthoritiesByUsernameQuery("SELECT username, 'ROLE_USER' as authority FROM doctor WHERE username = ?");
        userDetailsService.setRolePrefix(""); // Remove the default "ROLE_" prefix
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                // Add more access rules if needed
                .csrf().disable()
                .formLogin()
                .loginPage("/login") // Specify the login page URL
                .defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .logout() // Add logout configuration
                .logoutUrl("/logout") // Specify the logout URL
                .logoutSuccessUrl("/login") // Specify the logout success URL as the login page
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .httpBasic(withDefaults());
        return http.build();
    }
}