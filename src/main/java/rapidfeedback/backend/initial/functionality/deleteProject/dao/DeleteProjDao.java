package rapidfeedback.backend.initial.functionality.deleteProject.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;



/**
 * @author wmn
 * @date 04/05/2020
 */

@Mapper
@Repository
public interface DeleteProjDao {

    @Delete("DELETE FROM project WHERE id = #{project_id}")
    void deleteProject(@Param("project_id") Integer projectId);
}
