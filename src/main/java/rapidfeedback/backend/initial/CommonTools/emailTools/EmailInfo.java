package rapidfeedback.backend.initial.CommonTools.emailTools;

import lombok.Data;
import rapidfeedback.backend.initial.model.Project;

/**
 * @author cyt
 * @date 2020/5/25
 */
@Data
public class EmailInfo {
    public static String  createEmailContent(String subjectCode, String subjectName, String projectName,
                                             Double maxMark, Double score){
        return "Dear student,<br> <br> <br>" +

                "your " + projectName + " has been graded. In summary, you received " + score + " / "
                + maxMark + " marks for the presentation. Please find attached mark breakdown and feedback. <br> <br> <br>" +

                "Kind Regards,<br>" +
                "The " + subjectCode + " team";
    }
}
