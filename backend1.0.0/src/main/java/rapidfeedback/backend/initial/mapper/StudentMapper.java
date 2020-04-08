package rapidfeedback.backend.initial.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import rapidfeedback.backend.initial.model.Student;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {

    @Select("SELECT * FROM Marker")
    List<Student> findAll();
}
