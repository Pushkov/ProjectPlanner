package nicomed.tms.projectplanner.controller.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.annotations.AppMvc;
import nicomed.tms.projectplanner.services.PermissionService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@AppMvc
@RequiredArgsConstructor
@RequestMapping("")
public class ApplicationMvcController {

    private final PermissionService permissionService;

    @GetMapping("/")
    public String getStartPage() {
        System.out.println("MVC controller working");
        log.info("MVC controller working");
        return "index";
    }

    @GetMapping("/test")
    public String get1Page(ModelMap modelMap) {
        modelMap.addAttribute("perms", permissionService.findAll());

        System.out.println("MVC controller working");
        log.info("MVC controller working");
        return "start";
    }
}