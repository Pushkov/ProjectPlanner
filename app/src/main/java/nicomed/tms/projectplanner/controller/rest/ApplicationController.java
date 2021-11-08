package nicomed.tms.projectplanner.controller.rest;

import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.annotations.MyRest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@MyRest
@RequestMapping("/")
public class ApplicationController {

    @GetMapping("")
    public String getStartPage() {
        log.info("REST controller working");
        return "index";
    }
}