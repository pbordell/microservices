package com.pbs.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    // 1. Inyecta la clave simétrica que declaraste en tu application.yml
    @Value("${security.oauth2.resource.jwt.key-value}")
    private String signingKey;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // 4. Vincula el extractor de tokens personalizado a la configuración del recurso
        resources.tokenStore(tokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/console/**", "/h2/**").permitAll() // H2 console bypass
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().sameOrigin();
    }

    // 2. Define el almacén de tokens basado en JWT
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    // 3. Configura el convertidor inyectando la frase secreta de firma
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey);
        converter.setVerifierKey(signingKey);
        return converter;
    }
}
