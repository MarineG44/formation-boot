package sopra.formation.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Le cours n'a pas pu être validée")
public class CoursValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

}
