package nicomed.tms.projectplanner.controller.rest;

import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.annotations.AppRest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@AppRest
@RequestMapping("")
public class ApplicationController {

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

    @GetMapping("/planner/overview")
    public String getOverviewPage() {
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

    @GetMapping("/planner/projects")
    public String getProjectsPage() {
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

    @GetMapping("/planner/plans")
    public String getPlansPage() {
        log.info("REST controller working - plans");
        return "index";
    }

    @GetMapping("/planner/plans/{year}/{month}/{department_id}")
    public String getPlansPageWithId() {
        log.info("REST controller working - plans id");
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

}