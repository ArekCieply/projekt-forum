/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author Arek
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.formLogin().disable();
        security.csrf().disable();
        security.authorizeRequests()
                .antMatchers("/users/register").permitAll()
                .antMatchers("/users/login").permitAll()
                .antMatchers(HttpMethod.GET, "/users").permitAll()
                .antMatchers(HttpMethod.GET, "/users/find").permitAll()
                .antMatchers(HttpMethod.GET, "/section").permitAll()
                .antMatchers(HttpMethod.GET, "/sub_section").permitAll()
                .antMatchers(HttpMethod.GET, "/post").permitAll()
                .antMatchers(HttpMethod.GET, "/topic").permitAll()
                .antMatchers(HttpMethod.GET, "/rule").permitAll()
                .antMatchers("/users/ban").hasAuthority("moderator")
                .antMatchers("/section").hasAuthority("administrator")
                .antMatchers("/users/promote").hasAuthority("administrator")
                .antMatchers("/sub_section").hasAuthority("administrator")
                .antMatchers("/users/promote").hasAuthority("administrator")
                .antMatchers("/users/demote").hasAuthority("administrator")
                .antMatchers("/rule").hasAuthority("administrator") 
                .antMatchers("/post/delete").hasAuthority("moderator")
                .antMatchers("/post/edit").hasAuthority("moderator")
                .antMatchers("/topic/delete").hasAuthority("moderator")
                .antMatchers("/topic/edit").hasAuthority("moderator")
                .antMatchers("/post").hasAuthority("user")
                .antMatchers("/topic").hasAuthority("user")//todo update
                
                .and()
                .httpBasic();
    }
}
