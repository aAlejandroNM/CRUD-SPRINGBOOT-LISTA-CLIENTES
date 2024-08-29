package com.cliente.app.spring_boot_clienteapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig{
    @Autowired
    private DataSource dataSource;

    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/index", "/home", "/", "/css/**", "/js/**", "/img/**").permitAll()
                        .requestMatchers("/views/clientes/").hasRole("USER")
                        .requestMatchers("/views/clientes/create").hasRole("ADMIN")
                        .requestMatchers("/views/clientes/save").hasRole("ADMIN")
                        .requestMatchers("/views/clientes/edit/**").hasRole("ADMIN")
                        .requestMatchers("/views/clientes/delete/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true) // Redirige siempre a /home después de un login exitoso
                        .permitAll()
                );

        return http.build();
    }

    @Autowired
    public void configurerSecurityGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passEncoder)
                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT u.username, r.rol FROM roles r INNER JOIN users u ON r.user_id=u.id WHERE u.username=?");

    }

}
