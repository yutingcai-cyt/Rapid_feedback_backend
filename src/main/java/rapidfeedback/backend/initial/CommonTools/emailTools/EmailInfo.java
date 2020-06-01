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
        return "Dear " + subjectCode + " " + subjectName + " student,<br> <br> <br>" +

                "Attached is your mark breakdown and feedback for " + projectName + ". The assignment was marked out of " + score + " " +
                "and it makes up " + maxMark + " of your final subject mark. <br> <br> <br>" +

                "Please contact us if you have any further questions about your mark or feedback.<br>" +

                "Best wishes,<br>" +
                "The " + subjectCode + " " + subjectName + " team";
    }
}
