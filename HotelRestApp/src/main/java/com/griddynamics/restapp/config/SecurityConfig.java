package com.griddynamics.restapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/", "/hotel/**").hasAnyRole("GUEST", "ADMIN")
        .antMatchers("/admin/**").hasRole("ADMIN")
        .and()
        .httpBasic()
        .and()
        .csrf().disable()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .deleteCookies("JSESSIONID")
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .logoutSuccessUrl("/");

    http.headers().frameOptions().disable();
  }

  @Autowired
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    User.UserBuilder users = User.withDefaultPasswordEncoder();

    auth.inMemoryAuthentication()
        .withUser(users.username("John_Doe").password("guest1234").roles("GUEST"))
        .withUser(users.username("Jane_Doe").password("guest1235").roles("GUEST"))
        .withUser(users.username("_admin").password("admin1234").roles("ADMIN"));
  }
}
