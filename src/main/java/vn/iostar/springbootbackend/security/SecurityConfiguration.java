package vn.iostar.springbootbackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import vn.iostar.springbootbackend.entity.Role;
import vn.iostar.springbootbackend.security.jwt.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JWTAuthenticationFilter jwtAuthFilter;
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs", "/webjars/**" };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.headers()
                .addHeaderWriter(
                        new StaticHeadersWriter("Access-Control-Allow-Origin", "*")
                );
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/api/v1/user/forgot-password").permitAll()
                .antMatchers("/api/v1/admin/**").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.PATCH, "/api/v1/user/").hasRole(Role.ADMIN.name())
                .antMatchers("/api/v1/artist/**").hasRole(Role.ARTIST.name())
                .antMatchers("/api/v1/artists/**").hasRole(Role.ARTIST.name())
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
