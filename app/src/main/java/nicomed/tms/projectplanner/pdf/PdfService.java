package nicomed.tms.projectplanner.pdf;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.plan.PlanDto;
import org.springframework.core.io.ClassPathResource;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


@RequiredArgsConstructor
public class PdfService {

    private static final String PDF_RESOURCES = "/static/pdf_res";

    private final SpringTemplateEngine springTemplateEngine;

    public File generatePdf(PlanDto plan) throws IOException, DocumentException {
        Context context = getContext(plan);
        String html = loadAndFillTemplate(context);
        return renderPdf(html);
    }

    private File renderPdf(String html) throws IOException, DocumentException {
        File file = File.createTempFile("Plan_", ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.getFontResolver().addFont("/static/fonts/calibri.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }

    private Context getContext(PlanDto plan) {
        Context context = new Context();
        context.setVariable("test", "test тест");
        context.setVariable("department", plan.getDepartmentName());
        context.setVariable("year", plan.getYear());
        context.setVariable("month", plan.getMonth());
//        context.setVariable("points", plan.getPlanPointsDto());
        return context;
    }

    private String loadAndFillTemplate(Context context) {
        return springTemplateEngine.process("plan", context);
    }
}
