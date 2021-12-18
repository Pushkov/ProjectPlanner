package nicomed.tms.projectplanner.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nicomed.tms.projectplanner.dto.LoginDto;
import nicomed.tms.projectplanner.dto.UserDto;
import nicomed.tms.projectplanner.security.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/planner/api/v1/auth")
public class AuthenticationRestController {

    private final AuthService service;

    @PostMapping("/login")
    public UserDto login(@RequestBody LoginDto dto) {
        return service.getAuthToken(dto);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        service.logOut();
    }
}
