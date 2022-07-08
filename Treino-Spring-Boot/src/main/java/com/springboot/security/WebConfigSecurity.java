package com.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springboot.services.UserService;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
			.disable()
			.authorizeRequests()
				.antMatchers("/","/login").permitAll()
				.antMatchers("/register", "/registerUser").permitAll()
				.antMatchers(HttpMethod.GET, "/addDiscipline").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/addStudent").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/addTeacher").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/enrollTeacherDiscipline").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/enrolledDisciplineStudent").hasRole("STUDENT")
				.antMatchers(HttpMethod.GET, "/enrolledDisciplineTeacher").hasRole("TEACHER")
				.antMatchers(HttpMethod.GET, "/profile").hasAnyRole("STUDENT","TEACHER")
				.antMatchers(HttpMethod.GET, "/listDisciplines").hasAnyRole("STUDENT","ADMIN")
				.antMatchers(HttpMethod.GET, "/listStudents").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/listTeachers").hasRole("ADMIN")
				.anyRequest().authenticated()
			.and()
				.formLogin()
	    		.loginPage("/login").permitAll()
	    		.defaultSuccessUrl("/index")
	    	.and()
	    		.logout().permitAll()
	    		.logoutSuccessUrl("/login");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override //Ignora as urls especificas
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**");
	}
}
