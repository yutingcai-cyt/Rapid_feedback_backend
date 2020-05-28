package rapidfeedback.backend.initial.functionality.assess.dao;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import rapidfeedback.backend.initial.model.Comment;
import rapidfeedback.backend.initial.model.IndividualResult;

import java.util.List;

@Mapper
@Repository
public interface AssessDao {

    @Insert("INSERT INTO assessment(proj_id, marker_id, student_id, group_id, criteria_id, comment, score, assessed_date) " +
            "VALUES (#{projectId}, #{markerId}, #{studentId}, #{groupId}, #{criteriaId}, #{comment}, #{score}, #{assessedDate});")
    void setIndividualScore(@Param("projectId") Integer projectId, @Param("markerId") Integer markerId, @Param("studentId") Integer studentId,
                            @Param("groupId") Integer groupId, @Param("criteriaId") Integer criteriaId,
                  @Param("comment") String comment, @Param("score") Double score, @Param("assessedDate") String assessedDate);

    @Insert("INSERT INTO comment(marker_id, text, polarity) VALUES (#{comment.markerId}, #{comment.text}, #{comment.polarity});")
    @Options(useGeneratedKeys = true, keyProperty = "comment.id")
    Integer addComment(@Param("comment") Comment comment);

    @Select("SELECT * from comment WHERE marker_id = #{markerId}")
    List<Comment> getCommentList(@Param(value = "markerId") Integer markerId);

    @Select("SELECT m.id, m.first_name, m.last_name, a.assessed_date, c.name, p.weight, a.score, a.comment FROM ((assessment a JOIN marker m ON a.marker_id = m.id) JOIN criteria c ON a.criteria_id = c.id) JOIN proj_criteria p ON a.proj_id = p.proj_id AND c.id = p.criteria_id WHERE a.proj_id = #{projectId} AND a.student_id = #{studentId};")
    List<IndividualResult> getIndividualResult(@Param("projectId") Integer projectId, @Param("studentId") Integer studentId);

}
