package co.edu.poli.inalpes1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

import co.edu.poli.inalpes1.security.jwt.JwtEntryPoint;
import co.edu.poli.inalpes1.security.jwt.JwtTokenFilter;
import co.edu.poli.inalpes1.security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsServiceImpl userImpl;
	
	@Autowired
	JwtEntryPoint jwtEntryPoint;
	
	@Bean
	public JwtTokenFilter jwtTokenFilter() {
		return new JwtTokenFilter();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userImpl).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		.authorizeRequests()
		.antMatchers("/inalpes/api/auth/**").permitAll()
		.antMatchers(HttpMethod.GET,"/inalpes/api/Inmuebles").permitAll()
		.antMatchers(HttpMethod.GET,"/inalpes/api/Inmuebles/**").permitAll()
		.antMatchers("/inalpes/api/**").hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		/*
		 			.authorizeRequests()
		    .antMatchers(HttpMethod.GET,"/api/v1/Rooms").hasAnyRole("ADMIN", "USER")
		    .antMatchers(HttpMethod.GET,"/api/v1/Rooms/**").hasAnyRole("ADMIN", "USER")
		    .antMatchers(HttpMethod.DELETE,"/api/v1/**").hasAnyRole("ADMIN")
		    .antMatchers(HttpMethod.POST,"/api/v1/**").hasAnyRole("ADMIN")
		    .antMatchers(HttpMethod.PUT,"/api/v1/**").hasAnyRole("ADMIN")
			.and()
			.httpBasic(); //authentication method
			//.formLogin(); //authentication method

		 */
	}
	
	
}
