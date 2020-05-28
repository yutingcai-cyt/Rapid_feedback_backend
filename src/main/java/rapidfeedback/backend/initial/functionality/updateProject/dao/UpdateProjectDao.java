package rapidfeedback.backend.initial.functionality.updateProject.dao;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import rapidfeedback.backend.initial.model.Criteria;
import rapidfeedback.backend.initial.model.CriteriaContent;
import rapidfeedback.backend.initial.model.CriteriaData;
import rapidfeedback.backend.initial.model.Project;

import java.util.List;


/**
 * @author wmn
 * @date 04/05/2020
 */

@Mapper
@Repository
public interface UpdateProjectDao {

    @Update("UPDATE project SET subject_code = #{project.subject_code}, subject_name = #{project.subject_name}," +
            "proj_name = #{project.proj_name}, duration = #{project.duration}, due_date = #{project.due_date}, " +
            "is_group = #{project.is_group}, proj_description = #{project.proj_description} WHERE id = #{projectId}")
    void updateProject(@Param("project") Project project, @Param("projectId") Integer projectId);

    @Insert("INSERT INTO marker_in_proj(marker_id, proj_id) VALUES (#{markerId}, #{projectId})")
    void addMarker(@Param("markerId") Integer markerId, @Param("projectId") Integer projectId);

    @Select("SELECT marker_id FROM marker_in_proj WHERE proj_id = #{project_id}")
    List<Integer> getMarker(@Param("project_id") Integer projectId);

    @Insert("INSERT INTO criteria(name) VALUES (#{criteria.content})")
    @Options(useGeneratedKeys = true, keyProperty = "criteria.id")
    Integer addCriteria(@Param("criteria") CriteriaData criteria);

    @Insert("INSERT INTO proj_criteria(proj_id, criteria_id, weight) VALUES (#{project_id}, #{criteria_id}, #{weight})")
    void addCriteriaIntoProject(@Param("project_id") Integer projectId, @Param("criteria_id") Integer criteriaId, @Param("weight") Integer weight);

    @Update("UPDATE proj_criteria SET weight = #{weight} WHERE proj_id = #{projectId} AND criteria_id = #{criteriaId}")
    void updateCriteria(@Param("projectId") Integer projectId, @Param("criteriaId") Integer criteriaId, @Param("weight") Integer weight);

    @Select("SELECT p.criteria_id, c.name, p.weight FROM proj_criteria p LEFT JOIN criteria c ON p.criteria_id = c.id WHERE p.proj_id = #{projectId};")
    List<CriteriaContent> getCriteriaList(@Param("projectId") Integer projectId);

}
