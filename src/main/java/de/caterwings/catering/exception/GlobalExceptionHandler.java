package de.caterwings.catering.exception;

import de.caterwings.catering.constant.ErrorCodeEnum;
import de.caterwings.catering.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Slf4j
@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GlobalExceptionHandler {


    @ExceptionHandler({Throwable.class})
    public ResponseEntity<Object> handleException(Throwable ex) {
        log.error("{} ", ex);
        return new ResponseEntity<>(getErrorResponse(ex.getMessage(), Collections.emptyList()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({WebExchangeBindException.class})
    public ResponseEntity<Object> handleException(WebExchangeBindException ex) {
        log.error("{}", ex);
        List<ErrorResponse.ErrorInfo> errorInfos = new ArrayList<>();
        for (FieldError error : ex.getFieldErrors()) {
            errorInfos.add(ErrorResponse.ErrorInfo.builder().domain(error.getField())
                    .message(error.getDefaultMessage()).reason(ErrorCodeEnum.INVALID_PARAM.name()).build());
        }
        return new ResponseEntity<>(getErrorResponse(ErrorCodeEnum.INVALID_PARAM.name(), errorInfos), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CateringServiceException.class})
    public ResponseEntity<Object> handleException(CateringServiceException ex) {
        log.error("{} ", ex);
        List<ErrorResponse.ErrorInfo> errorInfos = new ArrayList<>();
        errorInfos.add(ErrorResponse.ErrorInfo.builder().reason(ex.getErrorEnum().toString()).message(ex.getMessage())
                .domain("").build());
        return new ResponseEntity<>(getErrorResponse(ex.getErrorEnum().toString(), errorInfos), ex.getHttpStatus());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handleException(IllegalArgumentException ex) {
        return new ResponseEntity<>(getErrorResponse(ex.getMessage(), Collections.emptyList()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleException(ConstraintViolationException ex) {
        ErrorResponse.ErrorInfo errorInfo = ErrorResponse.ErrorInfo.builder().message(ex.getMessage()).build();
        return new ResponseEntity<>(getErrorResponse(ex.getMessage(), Collections.singletonList(errorInfo)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(getErrorResponse(ex.getMessage(), Collections.emptyList()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(getErrorResponse(ex.getMessage(), Collections.emptyList()), HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse getErrorResponse(final String message, final List<ErrorResponse.ErrorInfo> errorInfos) {
        return ErrorResponse.builder().message(message).code(1000)
                .errors(errorInfos).build();
    }

}
