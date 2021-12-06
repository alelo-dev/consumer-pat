package br.com.alelo.consumer.consumerpat;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

@SpringBootApplication
public class ConsumerPatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerPatApplication.class, args);
	}
	
	@Bean
	  BeanFactoryPostProcessor beanFactoryPostProcessor(ApplicationContext beanRegistry) {
	      return beanFactory -> {
	          genericApplicationContext(
	            (BeanDefinitionRegistry) ((AnnotationConfigServletWebServerApplicationContext) beanRegistry)
	              .getBeanFactory());
	      };
	  }

	  void genericApplicationContext(BeanDefinitionRegistry beanRegistry) {
	      ClassPathBeanDefinitionScanner beanDefinitionScanner = new ClassPathBeanDefinitionScanner(beanRegistry);
	      beanDefinitionScanner.scan("br.com.alelo.consumer.consumerpat");
	  }

}
