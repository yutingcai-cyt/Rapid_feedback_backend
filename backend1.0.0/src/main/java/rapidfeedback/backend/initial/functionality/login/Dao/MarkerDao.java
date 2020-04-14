package rapidfeedback.backend.initial.functionality.login.Dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import rapidfeedback.backend.initial.model.Marker;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Mapper
@Repository
public interface MarkerDao {

//    String findByUsernameAndPassword(String pass);
//
//
//    int insertSingleMarker(Student student);

    @Select("SELECT * FROM Marker")
    List<Marker> findAll();

}
