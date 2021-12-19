package nicomed.tms.projectplanner.security;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.dto.LoginDto;
import nicomed.tms.projectplanner.dto.UserDto;
import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.security.jwt.JwtTokenProvider;
import nicomed.tms.projectplanner.services.EngineerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final EngineerService engineerService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public UserDto getAuthToken(@Valid LoginDto dto) {
        String username = dto.getLogin();
        String password = dto.getPassword();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        Engineer engineer = getEngineer(dto);

        String token = tokenProvider.createToken(username, engineer.getRole().getPermissions());

        return UserDto.builder()
                .id(engineer.getId())
                .login(username)
                .position(engineer.getRole().getName())
                .token(token)
                .locale(engineer.getSettings().getLocale().toLowerCase())
                .build();
    }

    protected Engineer getEngineer(LoginDto loginDto) {
        if (StringUtils.isNotEmpty(loginDto.getLogin()) && StringUtils.isNotEmpty(loginDto.getPassword())) {
            return engineerService.findByLogin(loginDto.getLogin())
                    .orElseThrow(() -> new UsernameNotFoundException("check the entered data (auth service)"));
        }
        return null;
    }

    public void logOut() {
        SecurityContextHolder.clearContext();
    }

}
