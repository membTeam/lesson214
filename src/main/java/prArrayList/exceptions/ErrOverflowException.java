package prArrayList.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class ErrOverflowException extends RuntimeException {
    public ErrOverflowException(String err) {
        super(err);
    }
}
