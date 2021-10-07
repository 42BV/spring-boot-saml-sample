package nl._42.samldemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SamlDemoConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/saml2/**").permitAll()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
            .and()
            .csrf()
                .ignoringAntMatchers("/saml2/**");
    }

}
