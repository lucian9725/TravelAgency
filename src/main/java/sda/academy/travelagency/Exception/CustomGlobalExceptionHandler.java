package sda.academy.travelagency.Exception;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sda.academy.travelagency.Exception.error.ApiError;
import sda.academy.travelagency.Exception.user.UserAlreadyTakenException;
import sda.academy.travelagency.Exception.user.UserNotFoundException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(InvalidFormatException exception, HttpServletRequest httpServletRequest)
    {
        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
        apiError.setMessage(exception.getMessage());
        apiError.setDebugMessage((ExceptionUtils.getRootCauseMessage(exception)));
        apiError.setPath(((ServletWebRequest)httpServletRequest).getRequest().getRequestURI());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception, HttpServletRequest httpServletRequest)
    {
        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.NOT_FOUND);
        apiError.setMessage(exception.getMessage());
        apiError.setDebugMessage((ExceptionUtils.getRootCauseMessage(exception)));
        apiError.setPath(httpServletRequest.getRequestURI());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(UserAlreadyTakenException.class)
    public ResponseEntity<Object> handleUserAlreadyTakenException(UserAlreadyTakenException exception, HttpServletRequest httpServletRequest)
    {
        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.IM_USED);
        apiError.setMessage(exception.getMessage());
        apiError.setDebugMessage((ExceptionUtils.getRootCauseMessage(exception)));
        apiError.setPath(httpServletRequest.getRequestURI());
        return buildResponseEntity(apiError);
    }
}