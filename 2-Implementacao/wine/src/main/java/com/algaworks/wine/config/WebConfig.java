package com.algaworks.wine.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//WebMvcConfigurerAdapter = facilita na configuração

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
	//Esse bean estará disponível no contexto do Spring. Para o spring utilizar nas configurações
	//Verifica se tem o objeto desse tipo, caso tenha então ele usa as config
	/**
	 * Apresentamos as páginas de erros
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer(){
		return (container -> 
			container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"),
									new ErrorPage(HttpStatus.FORBIDDEN, "/403"))
		);
		
	}
	
	//Usado para converter e entregar o objeto Vinho no método da controller "visualizar"
	@Bean
	public DomainClassConverter<FormattingConversionService> domainClassConverter(
			FormattingConversionService conversionService) {
		return new DomainClassConverter<FormattingConversionService>(conversionService);
	}
	
	//Se tentar acessar apenas o barra o sistema deve redirecionar para a tela de novo
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/vinhos/novo");
	}
}
