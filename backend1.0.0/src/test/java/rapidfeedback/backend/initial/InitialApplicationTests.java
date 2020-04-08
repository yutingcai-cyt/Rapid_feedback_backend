package rapidfeedback.backend.initial;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rapidfeedback.backend.initial.mapper.MarkerMapper;
import rapidfeedback.backend.initial.model.Marker;

import java.util.List;

@SpringBootTest
class InitialApplicationTests {


	@Autowired
	private MarkerMapper markerMapper;

	@Test
	void findAllTest(){
		List<Marker> list = markerMapper.findAll();
		for(Marker m : list){
			System.out.println(m);
		}
	}

}
