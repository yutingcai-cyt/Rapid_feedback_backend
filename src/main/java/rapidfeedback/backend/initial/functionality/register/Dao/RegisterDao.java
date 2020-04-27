package rapidfeedback.backend.initial.functionality.register.Dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import rapidfeedback.backend.initial.model.Marker;

/**
 * @author cyt
 * @date 2020/4/26
 */
@Mapper
@Repository
public interface RegisterDao  {

    @Insert("INSERT INTO marker(uni_id, uni_email, password, first_name,last_name,is_coordinator) " +
            "VALUES(#{marker.uni_id},#{marker.uni_email},#{marker.password},#{marker.first_name},#{marker.last_name},#{marker.is_coordinator})")
    @Options(useGeneratedKeys = true, keyProperty = "marker.id")
    Integer addMarker(@Param("marker")Marker marker);
}
