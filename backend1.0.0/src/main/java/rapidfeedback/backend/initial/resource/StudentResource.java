package rapidfeedback.backend.initial.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rapidfeedback.backend.initial.mapper.StudentMapper;
import rapidfeedback.backend.initial.model.Student;

import java.util.List;

@RestController
@RequestMapping("/rest/Student")
public class StudentResource {
    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/all")
    public List<Student> getAll() {
        return studentMapper.findAll();
    }
}
