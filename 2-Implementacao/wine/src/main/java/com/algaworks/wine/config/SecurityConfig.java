package com.algaworks.wine.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//WebSecurityConfigurerAdapter = facilita a configuração de segurança
//Serve para configuração de segurança
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("joao").password("joao").roles("CADASTRAR_VINHO").and()
			.withUser("maria").password("maria").roles("CADASTRAR_VINHO", "LISTAR_VINHO");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		//Os dois '**' é por causa do 'ant' que é uma ferramenta antiga de build do java (Apache Ant). 
		//Qualquer do layout pra frente está liberado.
			.antMatchers("/layout/**"); 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/vinhos/novo").hasRole("CADASTRAR_VINHO")
				.antMatchers("/vinhos/**").hasRole("LISTAR_VINHO")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
}
