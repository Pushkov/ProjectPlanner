package nicomed.tms.projectplanner.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class ApplicationController {

    @GetMapping("")
    public String getStartPage() {
        log.info("controller working");
        return "index";
    }
}