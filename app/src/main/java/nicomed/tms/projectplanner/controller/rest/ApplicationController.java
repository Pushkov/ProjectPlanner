package nicomed.tms.projectplanner.controller.rest;

import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.annotations.MyRest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@MyRest
@RequestMapping("")
public class ApplicationController {

    @GetMapping("/")
    public String getSlashPage() {
        log.info("REST controller working");
        return "redirect:/planner/index";
    }

    @GetMapping("/planner")
    public String getPlannerPage() {
        log.info("REST controller working");
        return "redirect:/planner/index";
    }

    @GetMapping("/planner/index")
    public String getPlannerIndexPage() {
        log.info("REST controller working");
        return "index";
    }

    @GetMapping("/planner/roles")
    public String getRolesPage() {
        log.info("REST controller working - roles");
        return "index";
    }

}