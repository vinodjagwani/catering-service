package de.caterwings.catering.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;


@Data
@EqualsAndHashCode(callSuper = true)
public final class CateringServiceException extends RuntimeException {

    private String message;

    private HttpStatus httpStatus;

    private ErrorPrinter errorEnum;

    public CateringServiceException(final ErrorPrinter errorEnum, final String message) {
        super(message);
        this.message = message;
        this.errorEnum = errorEnum;
        this.httpStatus = errorEnum.getHttpStatus();
    }

}
