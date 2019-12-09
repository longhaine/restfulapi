package com.restful.jwt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.restful.authentication.CustomAuthenticationEntryPoint;
import com.restful.authentication.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
	        return new JwtAuthenticationFilter();
	}
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    	return super.authenticationManagerBean();
	}
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider).eraseCredentials(false);
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/img/**");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		.headers().frameOptions().sameOrigin().and().authorizeRequests().and()
		.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
		.and()
		.authorizeRequests()
		.antMatchers("/account/doLogin").permitAll()
		.antMatchers("/product/popular").hasAuthority("1")
		.antMatchers("/chat/**").permitAll()
		.antMatchers("/topic/public").permitAll()
		.antMatchers("/hello").permitAll()
		.antMatchers("/img/**").permitAll()
		.anyRequest().authenticated();
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
