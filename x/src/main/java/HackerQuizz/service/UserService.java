package HackerQuizz.service;

import HackerQuizz.dto.UserRegisterDTO;
import HackerQuizz.model.AppUser;
import HackerQuizz.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
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
    @Lazy
    private final ProgressService progressService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ProgressService progressService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.progressService = progressService;
    }
    public void save(UserRegisterDTO dto){
        AppUser user = new AppUser();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }
    public void save(AppUser u){
        userRepository.save(u);
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
    public AppUser getByUsername(String username){
        return userRepository.getByUsername(username);
    }
    public void delete(AppUser u){
        // Deleting children first
        progressService.deleteByUser(u);
        // Then user itself
        userRepository.delete(u);
    }


}
