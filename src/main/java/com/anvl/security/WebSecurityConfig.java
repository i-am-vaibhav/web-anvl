/**
 * 
 */
package com.anvl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Vaibhav
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@ComponentScan("com.anvl")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// making login page and guest page
		http.authorizeRequests().antMatchers("/guest/**", "/authenticate").permitAll();

		// making spring security to validate all requests other than specified above
		http.authorizeRequests().anyRequest().authenticated();

		// adding custom form login and handlers
		http.userDetailsService(customUserDetailsService).formLogin().loginPage("/guest/login")
				.loginProcessingUrl("/authenticate").defaultSuccessUrl("/home", false).failureUrl("/guest/login?error");

		// configuring logout
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).clearAuthentication(true)
				.invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/guest/login?logout")
				.permitAll();

		// Session Management
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).sessionFixation()
				.migrateSession().sessionAuthenticationStrategy(registerSessionAuthStr()).maximumSessions(1)
				.sessionRegistry(sessionRegistry()).maximumSessions(1).expiredUrl("/guest/login?error")
				.maxSessionsPreventsLogin(true);
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public RegisterSessionAuthenticationStrategy registerSessionAuthStr() {
		return new RegisterSessionAuthenticationStrategy(sessionRegistry());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/META-INF/resources/**", "/webjars/**", "/js/**", "/css/**", "/images/**",
				"/templates/**", "/static/**");
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(BCryptVersion.$2A, 31);
	}

}
