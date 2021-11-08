package nicomed.tms.projectplanner.controller.mvc;

import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.annotations.MyMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@MyMvc
@RequestMapping("")
public class ApplicationController {

    @GetMapping("/")
    public String getStartPage() {
        System.out.println("MVC controller working");
        log.info("MVC controller working");
        return "index";
    }

    @GetMapping("/asd")
    public String get1Page() {
        System.out.println("MVC controller working");
        log.info("MVC controller working");
        return "index";
    }
}