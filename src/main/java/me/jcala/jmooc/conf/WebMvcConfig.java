package me.jcala.jmooc.conf;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;

@Configuration
@EnableJpaRepositories(basePackages = "me.jcala.jmooc.repository")
public class WebMvcConfig {
    @Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

		return (ConfigurableEmbeddedServletContainer container) ->{
				ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/html/401.html");
				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/html/404.html");
				ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/html/500.html");

				container.addErrorPages(error401Page, error404Page,error500Page);
		};
	}
}
