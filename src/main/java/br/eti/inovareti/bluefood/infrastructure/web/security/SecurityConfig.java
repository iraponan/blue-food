package br.eti.inovareti.bluefood.infrastructure.web.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/images/**", "/css/**", "/js/**", "/public", "/sbpay").permitAll()
                .antMatchers("/cliente/**").hasRole(Role.CLIENTE.toString())
                .antMatchers("/restautante/**").hasRole(Role.RESTAURANTE.toString())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login-error")
                    //.successHandler(null)
                    .permitAll()
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .permitAll();
    }
}