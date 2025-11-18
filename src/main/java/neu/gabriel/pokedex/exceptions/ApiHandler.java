package neu.gabriel.pokedex.exceptions;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiHandler {
    @ExceptionHandler(PokemonTypeException.class)
    public ResponseEntity<ErrorMessage> PokemonTypeException(PokemonTypeException ex, HttpServletRequest request) {
        ErrorMessage message = new ErrorMessage(ex.getMessage(), request, HttpStatus.UNPROCESSABLE_ENTITY.value());
        return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> PokemonTypeException (MethodArgumentNotValidException ex, HttpServletRequest request, BindingResult bindingResult){
        ErrorMessage message = new ErrorMessage(ex.getMessage(), request, HttpStatus.UNPROCESSABLE_ENTITY.value(), bindingResult);
        return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
