package rapidfeedback.backend.initial.functionality.login.Dao;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import rapidfeedback.backend.initial.model.Marker;
import rapidfeedback.backend.initial.model.Project;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Mapper
@Repository
public interface LoginDao {

    @Select("SELECT * FROM Marker")
    List<Marker> findAll();


    @Select("SELECT * FROM Marker WHERE email = #{username} and password = #{pwd}")
    Marker findByUsernameAndPwd(@Param("username") String username, @Param("pwd") String pwd);


    @Select("SELECT * FROM MarkerInProject " +
            "RIGHT JOIN Project ON MarkerInProject.idProject = Project.id " +
            "WHERE idMarker = #{id} OR idPrincipal = #{id}")
    List<Project> findProjectsById(@Param(value = "id") Integer id);
}
