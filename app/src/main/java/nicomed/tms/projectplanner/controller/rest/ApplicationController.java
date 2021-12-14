package nicomed.tms.projectplanner.controller.rest;

import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.annotations.AppRest;
import nicomed.tms.projectplanner.dto.plan.PlanDto;
import nicomed.tms.projectplanner.entity.Plan;
import nicomed.tms.projectplanner.exception.PdfGenerationErrorException;
import nicomed.tms.projectplanner.pdf.PdfService;
import nicomed.tms.projectplanner.services.PlanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static nicomed.tms.projectplanner.services.exception.ExceptionsProducer.throwNotFoundByIdException;

@RequiredArgsConstructor
@Slf4j
@AppRest
@RequestMapping("")
public class ApplicationController {

    private final PdfService pdfService;
    private final PlanService planService;

    @GetMapping("/planner/test")
    public String getPlannerTestPage() {
        log.info("REST controller working - TEST");
        return "test";
    }

    @GetMapping({"/", "/planner", "/planner/index"})
    public String getPlannerIndexPage() {
        log.info("REST controller working");
        return "index";
    }

    @GetMapping("/planner/login")
    public String getOverviewPage() {
        log.info("APP CONTROLLER LOGOUT");
        return "index";
    }


    @GetMapping("/planner/overview")
    public String getLogoutPage() {
        log.info("REST controller working - overview");
        return "index";
    }

    @GetMapping("/planner/roles")
    public String getRolesPage() {
        log.info("REST controller working - roles");
        return "index";
    }

    @GetMapping("/planner/departments")
    public String getDepartmentsPage() {
        log.info("REST controller working - departments");
        return "index";
    }

    @GetMapping("/planner/departments/{id}")
    public String getDepartmentsPageWithId(@PathVariable Long id) {
        log.info("REST controller working - departments");
        return "index";
    }

    @GetMapping("/planner/personal")
    public String getEngineersPage() {
        log.info("REST controller working - engineers");
        return "index";
    }

    @GetMapping({"/planner/projects", "/planner/projects/{id}"})
    public String getProjectsPage(@PathVariable(required = false) Long id) {
        log.info("REST controller working - projects");
        return "index";
    }

    @GetMapping("/planner/documents")
    public String getDocumentsPage() {
        log.info("REST controller working - documents");
        return "index";
    }

    @GetMapping("/planner/documents/{id}")
    public String getDocumentsPageWithId(@PathVariable Long id) {
        log.info("REST controller working - documents");
        return "index";
    }

    @GetMapping("/planner/title")
    public String getTitleListPage() {
        log.info("REST controller working - title_list");
        return "index";
    }

    @GetMapping({"/planner/plans", "/planner/planpoints"})
    public String getPlansPage() {
        log.info("REST controller working - plans");
        return "index";
    }

    @GetMapping("/planner/plans/{year}/{month}/{department_id}")
    public String getPlansPageWithId() {
        log.info("REST controller working - plans id");
        return "index";
    }

    @GetMapping("/planner/plans/{year}/{month}/{depId}/{id}")
    public String getPlanPointPage() {
        log.info("REST controller working - plan point");
        return "index";
    }

    @GetMapping("/planner/permissions")
    public String getPermissionsPage() {
        log.info("REST controller working - permissions");
        return "index";
    }

    @GetMapping("/planner/workshops")
    public String getWorkshopsPage() {
        log.info("REST controller working - workshops");
        return "index";
    }

    @GetMapping("/planner/plans/{year}/{month}/{depId}/export/pdf")
    public void exportToPDF(HttpServletResponse response,
                            @PathVariable Integer year,
                            @PathVariable Integer month,
                            @PathVariable Long depId) {
        if (Objects.isNull(year) || Objects.isNull(month) || Objects.isNull(depId)) {
            throwNotFoundByIdException(Plan.class, "");
        }

        PlanDto plan = planService.findByIdFields(year, month, depId);

        try {
            Path file = Paths.get(pdfService.generatePdf(plan).getPath());
            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition",
                        "attachment; filename=" + file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (DocumentException | IOException ex) {
            throw new PdfGenerationErrorException("wrong generation pdf file for plan");
        }
    }

}