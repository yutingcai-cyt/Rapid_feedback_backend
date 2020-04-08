package rapidfeedback.backend.initial.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rapidfeedback.backend.initial.mapper.ProjectMapper;
import rapidfeedback.backend.initial.model.Project;

import java.util.List;


@RestController
@RequestMapping("/rest/Project")
public class ProjectResource {
    @Autowired
    private ProjectMapper projectMapper;

    @GetMapping("/all")
    public List<Project> getAll() {
        return projectMapper.findAll();
    }
}
