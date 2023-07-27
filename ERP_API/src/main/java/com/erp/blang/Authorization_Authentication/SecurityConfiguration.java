package com.erp.blang.Authorization_Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Configuration
@Component
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired RequestFilter jwtRequestFilter;
	
	@Autowired UserService userService;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.cors()
		.and().csrf().disable()
		.authorizeRequests()
			.antMatchers("/**").permitAll()
//			.antMatchers("/api/v1/registration/**").permitAll()
//			.antMatchers("/api/v1/signin/**").permitAll()
//			.antMatchers("/api/v1/saveAdmin").permitAll()
		.antMatchers(HttpHeaders.ALLOW).permitAll()
		.antMatchers("/index.html").permitAll()
		.antMatchers("/index.jsp").permitAll()
		.mvcMatchers("/swagger-ui.html/**", "/configuration/**", "/swagger-resources/**", "/v2/api-docs","/webjars/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	} 
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
//		authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
//	}


}
