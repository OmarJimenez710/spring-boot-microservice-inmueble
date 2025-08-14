package com.vaxi.spring_boot_microservice_inmueble.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

// notación que hace que cuando se inicie la aplicación, se ejecute la lógica de autenticación 
@EnableWebSecurity
// indica que es una clase de configuración
@Configuration
public class SecurityConfig {
    // cuando un request llegue a la aplicación debe pasar primero por esta clase
    // y por las reglase de validación que se definan, eso hace la notación
    // @EnableWebSecurity

    // @Value --> con esta notación, extraemos un valor definido dentro de nuestro
    // archivo de configuración
    // (application.properties)
    // SECURITY_KEY_USER_NAME --> el valor se esa propiedad ahora esta en esta
    // variable
    @Value("${service.security.secure-key-username}")
    private String SECURITY_KEY_USERNAME;
    @Value("${service.security.secure-key-password}")
    private String SECURITY_KEY_PASSWORD;
    @Value("${service.security.secure-key-username-2}")
    private String SECURITY_KEY_USERNAME_2;
    @Value("${service.security.secure-key-password-2}")
    private String SECURITY_KEY_PASSWORD_2;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(
                        AuthenticationManagerBuilder.class);

        // proceso para settear los usuarios y sus respectivos passwords
        // --> .authorities: agregamos los roles
        // --> .passwordEncoder: indicamos que tipo de decodificación, vamos a utilizar
        // para nuestra contraseña
        // con las lineas de código siguientes, estoy cargando en memoria dos usuarios
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser(SECURITY_KEY_USERNAME)
                .password(new BCryptPasswordEncoder().encode(SECURITY_KEY_PASSWORD))
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"))
                .and()
                .withUser(SECURITY_KEY_USERNAME_2)
                .password(new BCryptPasswordEncoder().encode(SECURITY_KEY_PASSWORD_2))
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_DEV"))
                .and()
                .passwordEncoder(new BCryptPasswordEncoder());

        // /** --> indicamos con esto que todos nuestros endpoints estan protegidos
        // .authorizeRequests() --> que tenga una autorización de solicitud
        // .anyRequest() --> para quien va a solicitar esa autorización: para todas las
        // request
        // .csrf()
        // .disable() --> deshabilidamos el csrf: CrossSideRequestForgering activarla,
        // sirve para que los
        // hacker no puedan apropiarse de las sesiones, ahorita la deshabilitamos, ya
        // que
        // de momento, no estamos haciendo uso de sesiones
        http
                .securityMatcher("/**") // reemplazo de antMatcher
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().hasRole("ADMIN"))
                .csrf(csrf -> csrf.disable()); // desactiva CSRF

        return http.build();

    }

}
