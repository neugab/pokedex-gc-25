package neu.gabriel.pokedex.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashSet;
import java.util.Set;

public class ErrorMessage {
    private String message;
    private int status;
    private String path;
    Set<String> errors;

    public ErrorMessage(String message, HttpServletRequest request, int status) {
        this.message = message;
        this.path = request.getRequestURI();
        this.status = status;
    }

    public ErrorMessage(String message, HttpServletRequest request, int status, BindingResult result) {
        this.message = message;
        this.path = request.getRequestURI();
        this.status = status;
        this.errors = getErrors(result);
    }
    private Set<String> getErrors(BindingResult result){
        this.errors =new HashSet<>();
        for(FieldError fieldError :result.getFieldErrors()){
            errors.add(fieldError.getDefaultMessage());
        }
        return errors;
    }




}
