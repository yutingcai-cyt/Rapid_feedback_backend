package rapidfeedback.backend.initial.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rapidfeedback.backend.initial.mapper.MarkerMapper;
import rapidfeedback.backend.initial.model.Marker;

import java.util.List;

@RestController
@RequestMapping("/rest/Marker")
public class MarkerResource {

    @Autowired
    private MarkerMapper markerMapper;

    @GetMapping("/all")
    public List<Marker> getAll() {
        return markerMapper.findAll();
    }

}
