package rapidfeedback.backend.initial.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import rapidfeedback.backend.initial.model.Marker;

import java.util.List;

@Mapper
public interface MarkerMapper {

    @Select("SELECT * FROM Marker")
    List<Marker> findAll();
}
