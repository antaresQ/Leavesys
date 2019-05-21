package team.thirteen.leave;

import org.slf4j.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LapsApplication {

	Logger Log = LoggerFactory.getLogger(LapsApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(LapsApplication.class, args);
	}

}
