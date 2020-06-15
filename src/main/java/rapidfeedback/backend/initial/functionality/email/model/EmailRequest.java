package rapidfeedback.backend.initial.functionality.email.model;

import lombok.Data;

import java.util.List;

/**
 * @author cyt
 * @date 2020/5/28
 */
@Data
public class EmailRequest {
    Integer project_id;

    List<Integer> studentIdList;

    Integer option;
}
