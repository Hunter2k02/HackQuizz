package HackerQuizz.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/HackQuizz/login", "/HackQuizz/login-error", "/HackQuizz/logout", "/HackQuizz/passwordreminder", "/HackQuizz/register").permitAll()
                        .requestMatchers("login.css").permitAll()
                        .anyRequest().authenticated()

                )
                .formLogin(form -> form
                        .loginPage("/HackQuizz/login")
                        .loginProcessingUrl("/HackQuizz/login")
                        .defaultSuccessUrl("/HackQuizz/home")
                        .failureUrl("/HackQuizz/login?error")
                        .permitAll()
                )
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutUrl("/HackQuizz/logout")
                        .logoutSuccessUrl("/HackQuizz/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        var user = User.withUsername("admin")
                .password("{noop}admin")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
