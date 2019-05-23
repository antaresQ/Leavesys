package team.thirteen.leave;

import org.slf4j.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LeavesysApplication {

	Logger Log = LoggerFactory.getLogger(LeavesysApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(LeavesysApplication.class, args);
	}

}
