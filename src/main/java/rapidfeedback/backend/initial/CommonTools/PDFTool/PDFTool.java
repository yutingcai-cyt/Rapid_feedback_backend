package rapidfeedback.backend.initial.CommonTools.PDFTool;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import rapidfeedback.backend.initial.CommonTools.Exception.CommonError;
import rapidfeedback.backend.initial.CommonTools.Exception.FBException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author cyt
 * @date 2020/5/16
 */
@Service
public class PDFTool {

    public void createPDF(String filename){
        Document document = new Document(PageSize.A4);

        try{
            PdfWriter.getInstance(document,new FileOutputStream(filename));

            document.addTitle("example of PDF");
            document.open();
            document.add(new Paragraph("Hello World"));

        }catch (Exception e){
            throw new FBException(CommonError.INTERNAL_SERVER_ERROR.getResultCode(),e.getMessage());

        }finally {
            document.close();
        }
    }
}
