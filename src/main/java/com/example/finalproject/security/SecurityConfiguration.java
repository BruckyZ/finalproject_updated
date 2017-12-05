package com.example.finalproject.security;

import com.example.finalproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Autowired
	private SSUserDetailService userDetailService;

	@Autowired
	private UserRepository userRepository;


	@Override
	public UserDetailsService userDetailsServiceBean()throws Exception
	{
		return new SSUserDetailService(userRepository);
	}

	@Override
	protected  void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
				.antMatchers("/","/h2-console/**","/register","/addtrainer","/processtrainer","/css/**").permitAll()
//				.antMatchers("/","/h2-console/**","/register","/addstudentstoinstructors/**","/searchform/**").permitAll()
//					"/addcourse","/addinstructors","/addstudents").permitAll()
				.antMatchers("/addcourse","/addinstructors","/addstudents").access("hasAuthority('ADMIN')")
				.antMatchers("/secure").access("hasAuthority('ADMIN')")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login").permitAll()
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login").permitAll()
				.and()
				.httpBasic();
		http.csrf().disable();
		http.headers().frameOptions().disable();

	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception{

		auth.userDetailsService(userDetailsServiceBean());
//		 The below works for me before applying user service and data loder
//		auth.inMemoryAuthentication().withUser("user").password("pass").roles("USER");
//		auth.inMemoryAuthentication().withUser("admin").password("pass").roles("ADMIN");
	}

}