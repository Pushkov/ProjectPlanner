package nicomed.tms.projectplanner.security;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.services.EngineerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final EngineerService engineerService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        System.out.println(" user details name" + login);

        Engineer engineer = findByLogin(login);

        User user = new User(
                engineer.getLogin(),
                engineer.getPassword(),
                true,
                engineer.isEnabled(),
                engineer.isAccountNonExpired(),
                engineer.isAccountNonLocked(),
                convertToAuthorities(engineer.getRole().getName())

        );

        System.out.println(" user details login " + user.getUsername());
        System.out.println(" user details pwd " + user.getPassword());
        return user;
    }

    protected Engineer findByLogin(String login) {
        return engineerService.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Engineer not found"));

    }

    private Collection<? extends GrantedAuthority> convertToAuthorities(String role) {
//        if (role != null) {
//            return role.getPermissions().stream()
//                    .map(p -> new SimpleGrantedAuthority(p.getName()))
//                    .collect(Collectors.toSet());
//        } else {
//            return Collections.emptySet();
//        }
        if (role != null) {
            return Collections.singleton(new SimpleGrantedAuthority(role));
        } else {
            return Collections.emptySet();

        }
    }
}
