package HackerQuizz.service;

import HackerQuizz.model.AppUser;
import HackerQuizz.model.CustomUserDetails;
import HackerQuizz.repository.UserRepository;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> users = userRepository.findByUsername(username);


        if (users.isEmpty()){
            throw new UsernameNotFoundException(username);
        }
        AppUser user = users.get();
        System.out.println(user);
        return new CustomUserDetails(user);


    }
    private Collection<? extends GrantedAuthority> getAuthorities(AppUser user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }
}
