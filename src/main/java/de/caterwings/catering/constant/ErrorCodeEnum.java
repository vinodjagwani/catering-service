package de.caterwings.catering.constant;

import de.caterwings.catering.exception.ErrorPrinter;
import org.springframework.http.HttpStatus;

public enum ErrorCodeEnum implements ErrorPrinter {

    INVALID_PARAM(HttpStatus.BAD_REQUEST),
    ENTITY_NOT_FOUND(HttpStatus.BAD_REQUEST);


    ErrorCodeEnum(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    private HttpStatus httpStatus;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
