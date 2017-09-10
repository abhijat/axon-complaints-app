package am.complaints;

import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ComplaintsApplication {

    public static void main(String[] args) {
		SpringApplication.run(ComplaintsApplication.class, args);
	}
}
