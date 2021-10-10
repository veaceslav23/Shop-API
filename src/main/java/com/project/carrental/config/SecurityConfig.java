package com.project.carrental.config;

import com.project.carrental.security.jwt.JwtConfigurer;
import com.project.carrental.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN_ENDPOINT = "/api/admin/**";
    private static final String CARS_ENDPOINT = "/api/cars/**";
    private static final String INVOICES_ENDPOINT = "/api/invoices/**";
    private static final String REVENUE_ENDPOINT = "/api/revenue/**";
    private static final String AUTH_ENDPOINT = "/api/auth/**";
    private static final String BRAND_ENDPOINT = "/api/brands";
    private static final String IMAGES_ENDPOINT = "/api/images/**";
    private static final String ADMIN = "ADMIN";
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors()
            .and()
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(AUTH_ENDPOINT).permitAll()
            .antMatchers(HttpMethod.GET, CARS_ENDPOINT, BRAND_ENDPOINT, REVENUE_ENDPOINT).permitAll()
            .antMatchers(HttpMethod.DELETE, CARS_ENDPOINT).hasAuthority(ADMIN)
            .antMatchers(HttpMethod.POST, CARS_ENDPOINT).hasAuthority(ADMIN)
            .antMatchers(HttpMethod.PUT, CARS_ENDPOINT).hasAuthority(ADMIN)
            .antMatchers(IMAGES_ENDPOINT).permitAll()
            .antMatchers(ADMIN_ENDPOINT).hasAuthority(ADMIN)
            .anyRequest()
            .authenticated()
            .and()
            .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
