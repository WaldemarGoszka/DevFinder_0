package pl.devfinder.infrastructure.security;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import pl.devfinder.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.devfinder.business.UserService;
import pl.devfinder.infrastructure.database.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class UserSecurityServiceConfig implements UserDetailsService {


    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        pl.devfinder.domain.User user = userService.findByEmail(email);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    List.of(new SimpleGrantedAuthority(user.getRole().getRole())));
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
}
