package rapidfeedback.backend.initial.resource;


import org.apache.ibatis.jdbc.Null;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rapidfeedback.backend.initial.mapper.MarkerMapper;
import rapidfeedback.backend.initial.model.Marker;

import java.util.List;

@RestController
@RequestMapping("/rest/Marker")
public class MarkerResource {

    private MarkerMapper markerMapper;
    public MarkerResource(MarkerMapper markerMapper) {
        this.markerMapper = markerMapper;
    }

    @GetMapping("/all")
    public List<Marker> getAll() {
        return markerMapper.findAll();
    }

}
