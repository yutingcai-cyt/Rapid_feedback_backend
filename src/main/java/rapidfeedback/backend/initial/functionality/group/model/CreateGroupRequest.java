package rapidfeedback.backend.initial.functionality.group.model;

import lombok.Data;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**
 * @author cyt
 * @date 2020/5/4
 */
@Data

public class CreateGroupRequest {

    private Integer project_id;

    private Integer group_id;

    private List<StudentId> studentList;
}
