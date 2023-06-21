//package pl.devfinder.infrastructure.security;
//
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import pl.devfinder.domain.User;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//@Component
//public class UserSecurityDetails implements UserDetails {
//    private User user;
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(user.getRole().getRole()));
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUserName();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return user.getActive();
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
