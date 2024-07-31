package dev.aniket.StockPriceService.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class RestApiExceptionHandler {
    @ExceptionHandler(value = StockNotFoundException.class)
    public ResponseEntity<String> stockNotFoundException(StockNotFoundException se, WebRequest webRequest) {
        log.error(webRequest.getDescription(false));
        return new ResponseEntity<>(se.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> exception(Exception e, WebRequest webRequest) {
        log.error(webRequest.getDescription(false));
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
