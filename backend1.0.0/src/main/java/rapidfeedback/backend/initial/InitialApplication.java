package rapidfeedback.backend.initial;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rapidfeedback.backend.initial.model.Marker;

@MappedTypes(Marker.class)
@MapperScan("rapidfeedback.backend.initial.mapper")
@SpringBootApplication
public class InitialApplication {

	public static void main(String[] args) {
		SpringApplication.run(InitialApplication.class, args);
	}

}
