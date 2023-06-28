package pl.devfinder.infrastructure.security.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.devfinder.business.UserService;

import java.util.List;
@Slf4j
//@Component
//@AllArgsConstructor
//@NoArgsConstructor
public class UserDetailsServiceCustom implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        pl.devfinder.domain.User user = userService.findByEmail(email);
    log.info("Try login user: [{}]",user);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    List.of(new SimpleGrantedAuthority(user.getRole().getRole())));
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
}
