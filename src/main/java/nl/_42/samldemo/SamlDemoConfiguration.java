package nl._42.samldemo;

import nl._42.boot.onelogin.saml.web.Saml2Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;

@Configuration
@EnableWebSecurity
public class SamlDemoConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, Saml2Filter saml2Filter) throws Exception {
        http
            .addFilterBefore(saml2Filter, AnonymousAuthenticationFilter.class)
            .authorizeHttpRequests()
                .requestMatchers("/saml2/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/").permitAll()
                .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
            .and().csrf()
                .ignoringRequestMatchers("/saml2/**")
            .and().securityContext(securityContext ->
                securityContext
                    .requireExplicitSave(true)
                    .securityContextRepository(securityContextRepository())
            );

        return http.build();
    }

    @Bean
    public DelegatingSecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
            new RequestAttributeSecurityContextRepository(),
            new HttpSessionSecurityContextRepository()
        );
    }

}
