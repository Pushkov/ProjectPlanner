package nicomed.tms.projectplanner.security;

import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SecurityUserFactory {

    public static User createUserFrom(Engineer engineer) {
        return new User(
                engineer.getLogin(),
                engineer.getPassword(),
                true,
                engineer.isEnabled(),
                engineer.isAccountNonExpired(),
                engineer.isAccountNonLocked(),
                mapAuthorities(engineer.getRole()));
    }

    private static List<GrantedAuthority> mapAuthorities(Role role) {
        return role.getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toList());
    }
}
