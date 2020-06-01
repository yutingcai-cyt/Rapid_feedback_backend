package rapidfeedback.backend.initial.functionality.createProject.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import rapidfeedback.backend.initial.model.Project;


/**
 * @author wmn
 * @date 02/05/2020
 */

@Mapper
@Repository
public interface CreateProjectDao {

    @Insert("INSERT INTO project(subject_code, subject_name, proj_name, duration, due_date, is_group, proj_description) " +
            "VALUES(#{project.subject_code}, #{project.subject_name}, #{project.proj_name}, #{project.duration}, " +
            "#{project.due_date}, #{project.is_group}, #{project.proj_description})")
    @Options(useGeneratedKeys = true, keyProperty = "project.id")
    Integer createProject(@Param("project") Project project);

    @Insert("INSERT INTO marker_in_proj(marker_id, proj_id) VALUES(#{markerId}, #{projectId})")
    void linkMarkerAndProj(@Param("markerId") Integer markerId, @Param("projectId") Integer projectId);

    @Insert("INSERT INTO proj_criteria(proj_id, criteria_id, weight) " +
            "VALUES(#{projectId}, 1, 10), (#{projectId}, 2, 10), (#{projectId}, 3, 10), " +
            "(#{projectId}, 4, 10), (#{projectId}, 5, 10)")
    void linkProjAndCriteria(@Param("projectId") Integer projectId);


    @Select("SELECT * FROM project where id = #{projectId}")
    Project getProjectById(@Param("projectId") Integer projectId);

}
