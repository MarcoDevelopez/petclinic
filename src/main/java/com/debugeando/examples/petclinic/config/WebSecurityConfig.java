package com.debugeando.examples.petclinic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.debugeando.examples.petclinic.security.JwtAuthenticationEntryPoint;
import com.debugeando.examples.petclinic.security.JwtAuthenticationTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtAuthenticationEntryPoint unauthorizedHandler;

  @Autowired
  public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
    return new JwtAuthenticationTokenFilter();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        // we don't need CSRF because our token is invulnerable
        .csrf().disable()

        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

        // don't create session
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

        .authorizeRequests()
        //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

        // allow anonymous resource requests
        .antMatchers(
            HttpMethod.GET,
            "/",
            "/*.html",
            "/favicon.ico",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js",
            "/assets/**", 
						"/inline.bundle.js",
						"/polyfills.bundle.js",
						"/styles.bundle.js",
						"/vendor.bundle.js",
						"/main.bundle.js"
        ).permitAll()
        
        // allow url no api request
        .antMatchers("/*").permitAll()
        
        // allow authentication urls
        .antMatchers("/auth/**", "/login**", "/login/**").permitAll()
        
        // block urls api request
        .antMatchers("/api/**").authenticated();
        
        //.anyRequest().authenticated();

    // Custom JWT based security filter
    http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

    // disable page caching
    http.headers().cacheControl();
  }

}
