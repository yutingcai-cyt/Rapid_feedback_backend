package rapidfeedback.backend.initial.functionality.group.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
/**
 * @author cyt
 * @date 2020/5/4
 */


@Mapper
@Repository
public interface GroupDao {

    @Update("UPDATE student_in_proj SET group_id = #{group} where proj_id = #{projectId} and student_id = #{studentId}")
    void addStudentIntoGroup(@Param("studentId") Integer studentId, @Param("projectId") Integer projectId, @Param("group") Integer group);

    @Update("UPDATE student_in_proj SET group_id = 0 where proj_id = #{projectId} and group_id = #{group}")
    void deleteGroup(@Param("projectId") Integer projectId, @Param("group") Integer group);


    @Select("select student_id from student_in_proj where student_id = #{studentId} and proj_id = #{projectId}")
    Integer findStudentInProject(@Param("studentId") Integer studentId, @Param("projectId") Integer projectId);
}
