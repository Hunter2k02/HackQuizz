package HackerQuizz.service;

import HackerQuizz.dto.UserRegisterDTO;
import HackerQuizz.model.AppUser;
import HackerQuizz.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void save(UserRegisterDTO dto){
        AppUser user = new AppUser();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setRole("ROLE_USER");
        userRepository.save(user);

    }

    public Optional<AppUser> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

}
