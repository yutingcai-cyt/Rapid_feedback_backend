package rapidfeedback.backend.initial.functionality.student.Dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import rapidfeedback.backend.initial.functionality.student.model.getStudentResponse;
import rapidfeedback.backend.initial.model.Student;

import java.util.List;

/**
 * @author cyt
 * @date 2020/4/29
 */
@Mapper
@Repository
public interface StudentDao {

    @Insert("INSERT INTO student(uni_student_number,first_name, last_name, uni_email) " +
            "VALUES(#{student.uni_student_number}, #{student.first_name}, #{student.last_name}, #{student.uni_email})")
    @Options(useGeneratedKeys = true, keyProperty = "student.id")
    Integer addStudent(@Param("student") Student student);

    @Delete("DELETE FROM student_in_proj where student_id = #{studentId} and proj_id = #{projectId}")
    void deleteStudent(@Param(value = "studentId")Integer studentId, @Param("projectId") Integer projectId);

    @Select("SELECT * from student where student.uni_student_number = #{id}")
    Student getStudentByStudentNumber(@Param("id") Integer uni_student_number);

    @Select("SELECT * from student INNER JOIN student_in_proj ON student.id = student_in_proj.student_id where proj_id = #{projectId}" )
    List<getStudentResponse> getStudentsByProjectId(@Param("projectId") Integer projectId);

    @Insert("INSERT INTO student_in_proj(proj_id, student_id, group_id) " +
            "VALUES(#{projectId}, #{studentId},0)")
    void addStudentIntoProject(@Param("studentId") Integer studentId, @Param("projectId") Integer projectId);



}
