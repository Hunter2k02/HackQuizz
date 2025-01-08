package HackerQuizz.securityComponents;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Get user roles
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String targetUrl = "/HackQuizz/home";

        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();
            if (role.equals("ROLE_ADMIN")) {
                targetUrl = "/HackQuizz/admin-home";
                break;
            } else if (role.equals("ROLE_USER")) {
                targetUrl = "/HackQuizz/home";
                break;
            }
        }

        // Redirect to the determined URL
        response.sendRedirect(targetUrl);
    }
}