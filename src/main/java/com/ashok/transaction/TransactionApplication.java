package com.ashok.transaction;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(scanBasePackages = {"com.ashok.transaction.*"})
@ComponentScan("com.ashok.transaction") // Adjust the package name accordingly
@EntityScan({"com.ashok.transaction.entity"})
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class TransactionApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
      SpringApplication.run(TransactionApplication.class, args);
  }


  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(TransactionApplication.class);
  }

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    super.onStartup(servletContext);
  }

}
