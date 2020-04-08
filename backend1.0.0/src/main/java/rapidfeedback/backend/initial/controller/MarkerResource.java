package rapidfeedback.backend.initial.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import rapidfeedback.backend.initial.mapper.MarkerMapper;
import rapidfeedback.backend.initial.model.Marker;

import java.util.List;

@RestController
@RequestMapping("/rest/Student")
public class MarkerResource {

    @Autowired
    private MarkerMapper markerMapper;


    @Autowired
    WebApplicationContext webApplicationContext;
    @GetMapping("/all")
    public List<Marker> getAll() {

        return markerMapper.findAll();
    }

}
