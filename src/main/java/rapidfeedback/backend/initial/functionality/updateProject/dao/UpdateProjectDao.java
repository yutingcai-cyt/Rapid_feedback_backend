package rapidfeedback.backend.initial.functionality.updateProject.dao;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import rapidfeedback.backend.initial.model.Project;


/**
 * @author wmn
 * @date 04/05/2020
 */

@Mapper
@Repository
public interface UpdateProjectDao {

    @Update("UPDATE project SET subject_code = #{project.subject_code}, subject_name = #{project.subject_name}," +
            "proj_name = #{project.proj_name}, duration_min = #{project.duration_min}, duration_sec = #{project.duration_sec}," +
            "is_group = #{project.is_group}, proj_description = #{project.proj_description} WHERE id = #{project_id}")
    void updateProject(@Param("project") Project project, @Param("project_id") Integer projectId);

    @Insert("INSERT INTO marker_in_proj(marker_id, proj_id) VALUES (#{marker_id}, #{proj_id});")
    void addMarker(@Param("marker_id") Integer markerId, @Param("proj_id") Integer projectId);

    @Update("UPDATE proj_criteria SET weight = #{weight} WHERE proj_id = #{proj_id} AND criteria_id = #{criteria_id}")
    void updateCriteria(@Param("project_id") Integer projectId, @Param("criteria_id") Integer criteriaId);
}
