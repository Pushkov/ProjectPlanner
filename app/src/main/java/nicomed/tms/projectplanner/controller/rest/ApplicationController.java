package nicomed.tms.projectplanner.controller.rest;

import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.annotations.AppRest;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/planner/roles")
    public String getRolesPage() {
        log.info("REST controller working - roles");
        return "index";
    }

    @GetMapping("/planner/personal")
    public String getEngineersPage() {
        log.info("REST controller working - engineers");
        return "index";
    }

    @GetMapping("/planner/title")
    public String getTitleListPage() {
        log.info("REST controller working - title_list");
        return "index";
    }

    @GetMapping("/planner/plans")
    public String getPlansPage() {
        log.info("REST controller working - planss");
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

    @GetMapping("/planner/permissions")
    public String getPermissionsPage() {
        log.info("REST controller working - permissions");
        return "index";
    }


}