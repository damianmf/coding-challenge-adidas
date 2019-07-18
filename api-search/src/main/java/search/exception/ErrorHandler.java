package search.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler
        extends ResponseEntityExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

//    todo: handle 4XX exceptions

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> defaultException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "An error happend, we are solving it";
        logger.error(bodyOfResponse,ex);
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = { AdidasApiException.class })
    protected ResponseEntity<Object> addidasException(Exception ex, WebRequest request) {
        logger.error("Api handled exception",ex);
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
