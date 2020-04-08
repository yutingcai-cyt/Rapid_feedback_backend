package rapidfeedback.backend.initial.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rapidfeedback.backend.initial.mapper.CriterionMapper;
import rapidfeedback.backend.initial.model.Criterion;

import java.util.List;

@RestController
@RequestMapping("/rest/Criterion")
public class CriterionResource {
    @Autowired
    private CriterionMapper criterionMapper;

    @GetMapping("/all")
    public List<Criterion> getAll() {
        return criterionMapper.findAll();
    }

}
