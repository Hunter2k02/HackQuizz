package HackerQuizz.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(final AccessDeniedException e) {
        return "error/403";
    }
}
