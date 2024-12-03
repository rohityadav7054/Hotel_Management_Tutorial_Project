package Com.hotel.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hotel.manage"})
public class SpringBootReactProject1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactProject1Application.class, args);
	}

}
