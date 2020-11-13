package com.example.truecapp3.config;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.example.truecapp3.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  UserService userService;


  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());

  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.
            headers().frameOptions().sameOrigin().and()
            .authorizeRequests().antMatchers("/css/**", "/js/**", "/libs/**", "/assets/**", "/img/**", "/register", "/registrar", "empresa", "home123", "*/verificarUsuario/**").permitAll().and()
            .formLogin().loginPage("/login").loginProcessingUrl("/logincheck").usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/Inicio").permitAll().permitAll().and()
            .logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll().and()
            .csrf().disable()
            .sessionManagement().maximumSessions(1);

  }
}
