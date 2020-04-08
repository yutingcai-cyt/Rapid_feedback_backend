package rapidfeedback.backend.initial.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import rapidfeedback.backend.initial.model.Criterion;

import java.util.List;

@Mapper
@Repository
public interface CriterionMapper {
    @Select("SELECT * FROM Project")
    List<Criterion> findAll();
}
