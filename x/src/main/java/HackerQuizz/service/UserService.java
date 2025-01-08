package HackerQuizz.service;

import HackerQuizz.dto.UserRegisterDTO;
import HackerQuizz.model.AppUser;
import HackerQuizz.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void save(UserRegisterDTO dto){
        AppUser user = new AppUser();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setRole("ROLE_USER");
        userRepository.save(user);

    }
    public AppUser getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof AppUser){
            return (AppUser) principal;
        }else if (principal instanceof UserDetails){
            String username = ((UserDetails) principal).getUsername();
            // Fetch the AppUser from the database
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User with given username: " + username + "not found"));

        }
        return null;
    }

    public Optional<AppUser> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public List<AppUser> getAllUsers(){
        return userRepository.findAll();
    }

}
