package com.example.companyemployeespring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin().and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/addCompany").authenticated()
                .antMatchers(HttpMethod.GET, "/addEmployee").authenticated()
                .antMatchers(HttpMethod.GET, "/deleteCompany/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/deleteEmployee/**").hasAnyRole("ADMIN");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("poxos")
                .password(encoder().encode("poxos"))
                .roles("USER")
                .and()
                .withUser("petros")
                .password(encoder().encode("petros"))
                .roles("ADMIN");
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}

