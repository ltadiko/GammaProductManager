package nl.intergamma.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * The @EnableResourceServer annotation adds a filter of type OAuth2AuthenticationProcessingFilter automatically
 * to the Spring Security filter chain.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/", "/home", "/register", "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.PUT, "/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.DELETE, "/**").access("hasRole('ROLE_ADMIN')")
                .and().csrf().disable();

    }
}
