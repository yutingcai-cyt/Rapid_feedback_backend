package rapidfeedback.backend.initial.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import rapidfeedback.backend.initial.model.Marker;

import java.util.List;

@Mapper
@Repository
public interface MarkerMapper {

    @Select("SELECT * FROM Marker")
    List<Marker> findAll();
}
