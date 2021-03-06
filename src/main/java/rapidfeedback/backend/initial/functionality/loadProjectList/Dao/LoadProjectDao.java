package rapidfeedback.backend.initial.functionality.loadProjectList.Dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import rapidfeedback.backend.initial.model.Project;

import java.util.List;

@Mapper
@Repository
public interface LoadProjectDao {

    @Select("SELECT * FROM project")
    List<Project> findAll();

    @Select("SELECT * from project " +
            "LEFT JOIN marker_in_proj ON marker_in_proj.proj_id = project.id " +
            "WHERE marker_id = #{id}")
    List<Project> findProjectsById(@Param(value = "id") Integer id);

}
