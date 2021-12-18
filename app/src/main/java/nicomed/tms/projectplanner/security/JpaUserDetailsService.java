package nicomed.tms.projectplanner.security;

import lombok.RequiredArgsConstructor;
import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.services.EngineerService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static nicomed.tms.projectplanner.security.SecurityUserFactory.createUserFrom;

@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final EngineerService engineerService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        System.out.println("input login: " + login);

        Engineer engineer = findByLogin(login);

        User user = createUserFrom(engineer);

        System.out.println("user details login: " + user.getUsername());
        System.out.println("user details pwd: " + user.getPassword());
        return user;
    }

    protected Engineer findByLogin(String login) {
        return engineerService.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Engineer not found"));
    }
}
