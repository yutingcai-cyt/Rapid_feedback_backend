package rapidfeedback.backend.initial.functionality.createProject.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import rapidfeedback.backend.initial.model.Project;


/**
 * @author wmn
 * @date 02/05/2020
 */

@Mapper
@Repository
public interface CreateProjectDao {

    @Insert("INSERT INTO project(subject_code, subject_name, proj_name, duration_min, duration_sec, is_group, proj_description) " +
            "VALUES(#{project.subject_code},#{project.subject_name},#{project.proj_name},#{project.duration_min},#{project.duration_sec},#{project.is_group},#{project.proj_description})")
    @Options(useGeneratedKeys = true, keyProperty = "project.id")
    Integer createProject(@Param("project") Project project);
}
