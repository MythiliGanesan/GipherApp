package com.stackroute.giphermanager;

import com.stackroute.giphermanager.Filter.JwtFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(scanBasePackages = {"com.stackroute.giphermanager"})
public class GipherManagerApplication {
	
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/*");
		return registrationBean;
	}


	public static void main(String[] args) {
		SpringApplication.run(GipherManagerApplication.class, args);
		System.out.println("Welcome to GipherManager Application");
	}

}



