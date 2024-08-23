package com.cliente.app.spring_boot_clienteapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig{
    @Autowired
    private DataSource dataSource;

    @Autowired
    private BCryptPasswordEncoder passEncoder;


    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().requestMatchers("/index","/home","/","/css/**","/js/**","/images/**")
                .permitAll().requestMatchers("/views/clientes/").hasAnyRole("USER")
                .requestMatchers("/views/clientes/create").hasAnyRole("ADMIN")
                .requestMatchers("/views/clientes/save").hasAnyRole("ADMIN")
                .requestMatchers("/views/clientes/edit/**").hasAnyRole("ADMIN")
                .requestMatchers("/views/clientes/delete/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated();

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
