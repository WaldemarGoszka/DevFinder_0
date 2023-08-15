package pl.devfinder.infrastructure.security.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.devfinder.business.UserService;
import pl.devfinder.domain.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
//@AllArgsConstructor
//@NoArgsConstructor
public class UserDetailsServiceCustom implements UserDetailsService {

   // @Autowired
   private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Process login user, email: [{}]", email);
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()) {
            log.info("Building UserDetailCustom: [{}]", user);
            return new UserDetailsCustom(user.get());
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
}
