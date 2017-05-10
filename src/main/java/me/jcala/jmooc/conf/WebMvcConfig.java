package me.jcala.jmooc.conf;

import me.jcala.jmooc.interceptor.UserSecurityInterceptor;
import me.jcala.jmooc.utils.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import java.io.File;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {


	@Value("${jmooc.basic_home}")
	private String basicHome;

	@Value("${jmooc.basic_url")
	private String basicUrl;


	private UserSecurityInterceptor securityInterceptor;

	@Autowired
	public WebMvcConfig(UserSecurityInterceptor securityInterceptor) {
		this.securityInterceptor = securityInterceptor;
	}

    /*@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

		return (ConfigurableEmbeddedServletContainer container) ->{
				ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/html/401.html");
				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/html/404.html");
				ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/html/500.html");

				container.addErrorPages(error401Page, error404Page,error500Page);
		};
	}*/

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(securityInterceptor).addPathPatterns("/user/**");//配置登录拦截器拦截路径
	}

	@PostConstruct
	public void initConfigureBean(){
		FileType.FILE.setHome(basicHome+ File.separatorChar+"files"+File.separatorChar);
		FileType.FILE.setHome(basicUrl+"/files/");
		FileType.VIDEO.setHome(basicHome+ File.separatorChar+"videos"+File.separatorChar);
		FileType.VIDEO.setHome(basicUrl+"/videos/");
	}
}
