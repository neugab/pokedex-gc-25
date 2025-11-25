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
    public ResponseEntity<ErrorMessage> handlePokemonTypeException(
            PokemonTypeException ex,
            HttpServletRequest request) {
        ErrorMessage message = new ErrorMessage(
                ex.getMessage(),
                request,
                HttpStatus.UNPROCESSABLE_ENTITY.value()
        );
        return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity<ErrorMessage> handlePokemonNotFoundException(
            PokemonNotFoundException ex,
            HttpServletRequest request) {
        ErrorMessage message = new ErrorMessage(
                ex.getMessage(),
                request,
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request,
            BindingResult bindingResult) {
        ErrorMessage message = new ErrorMessage(
                "Erro de validação nos campos",
                request,
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                bindingResult
        );
        return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGenericException(
            Exception ex,
            HttpServletRequest request) {
        ErrorMessage message = new ErrorMessage(
                "Erro interno do servidor: " + ex.getMessage(),
                request,
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}