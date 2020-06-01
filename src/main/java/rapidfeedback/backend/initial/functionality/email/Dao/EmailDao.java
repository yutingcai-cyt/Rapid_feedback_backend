package rapidfeedback.backend.initial.functionality.email.Dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.BlobTypeHandler;
import org.springframework.stereotype.Repository;
import rapidfeedback.backend.initial.functionality.email.model.Assessment;
import rapidfeedback.backend.initial.functionality.email.model.AudioRequest;
import rapidfeedback.backend.initial.functionality.email.model.CriteriaInfo;
import rapidfeedback.backend.initial.model.Marker;
import rapidfeedback.backend.initial.model.Project;
import rapidfeedback.backend.initial.model.Student;

import java.sql.Blob;
import java.util.List;

/**
 * @author cyt
 * @date 2020/5/28
 */

@Repository
@Mapper
public interface EmailDao {

    @Select("SELECT * FROM project where id = #{projectId};")
    Project getProjectInfo(@Param("projectId") Integer projectId);

    @Select("SELECT * FROM marker INNER JOIN marker_in_proj ON marker_id = marker.id WHERE proj_id = #{projectId} AND is_coordinator = 1")
    Marker getCoordinator(@Param("projectId") Integer projectId);

    @Select("SELECT * FROM assessment WHERE proj_id = #{projectId} AND student_id = #{studentId} AND marker_id = #{coordinatorId}")
    List<Assessment> getAssessmentResult(@Param("projectId") Integer projectId, @Param("studentId") Integer studentId, @Param("coordinatorId")Integer coordinatorId);

    @Select("SELECT * FROM criteria INNER JOIN proj_criteria ON id = criteria_id WHERE proj_id = #{projectId} AND id = #{criteriaId}")
    CriteriaInfo getCriteriaInfo(@Param("projectId") Integer projectId, @Param("criteriaId") Integer criteriaId);

    @Select("SELECT * FROM student WHERE id = #{studentId}")
    Student getStudentById(@Param("studentId") Integer studentId);

    @Select("SELECT * FROM marker_in_proj INNER JOIN marker ON marker_id = id WHERE proj_id = #{projectId}")
    List<Marker> getAllMarkersOfProject(@Param("projectId") Integer projectId);

    @Insert("INSERT INTO audio(marker_id, proj_id, student_id, bin_data, file_type) VALUES(#{markerId},#{projectId}, #{studentId}, #{audio,jdbcType=BLOB,typeHandler = org.apache.ibatis.type.BlobTypeHandler}, #{type})")
    Integer addAudio(@Param("projectId") Integer projectId,@Param("studentId") Integer studentId, @Param("markerId") Integer markerId,@Param("audio") Object audio, @Param("type") String file_type);

    @Select("SELECT * FROM audio WHERE marker_id = #{markerId} AND proj_id = #{projectId} AND student_id = #{studentId}")
    AudioRequest getAudio(@Param("markerId") Integer markerId, @Param("projectId") Integer projectId, @Param("studentId") Integer studentId);

    @Update("UPDATE audio set bin_data = #{audio} WHERE marker_id = #{markerId} AND proj_id = #{projectId} AND student_id = #{studentId}")
    Boolean updateAudio(@Param("audio") Object audio, @Param("studentId") Integer studentId, @Param("markerId") Integer markerId, @Param("projectId") Integer projectId);
}
