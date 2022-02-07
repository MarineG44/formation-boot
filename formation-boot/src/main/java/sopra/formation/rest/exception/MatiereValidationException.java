package sopra.formation.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "La matière n'a pas pu être validée")
public class MatiereValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

}
