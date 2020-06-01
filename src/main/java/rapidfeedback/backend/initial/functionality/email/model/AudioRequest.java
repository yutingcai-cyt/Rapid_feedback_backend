package rapidfeedback.backend.initial.functionality.email.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author cyt
 * @date 2020/6/1
 */
@Data
public class AudioRequest {

    byte[] bin_data;
}
