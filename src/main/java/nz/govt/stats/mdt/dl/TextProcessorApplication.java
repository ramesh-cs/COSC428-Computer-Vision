package nz.govt.stats.mdt.dl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@ComponentScan(basePackages = {"nz.govt.stats.mdt.dl"})
public class TextProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TextProcessorApplication.class, args);
	}

}
