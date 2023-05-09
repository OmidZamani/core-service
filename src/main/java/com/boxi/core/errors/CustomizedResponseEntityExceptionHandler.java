package com.boxi.core.errors;



import com.boxi.core.response.Response;
import com.ctc.wstx.shaded.msv_core.verifier.ErrorInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
@RestController
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public CustomizedResponseEntityExceptionHandler() {
        super();
    }

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(BusinessException.EntityNotFoundException.class)
    public final ResponseEntity handleNotFountExceptions(Exception ex, WebRequest request) {
        Response response = Response.notFound();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

        ex.printStackTrace();
        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        Response response = Response.badRequest();
        response.addErrorMsgToResponse(errors.toString(), ex);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
       // return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
     }


    @ExceptionHandler(BusinessException.DuplicateEntityException.class)
    public final ResponseEntity handleNotFountExceptions1(Exception ex, WebRequest request) {
        Response response = Response.duplicateEntity();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = BusinessException.CustomException.class)
    protected ResponseEntity handleBusinessException(RuntimeException ex, WebRequest request) {
        Response response = Response.validationException();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorInfo> duplicateEmailException(HttpServletRequest req, DataIntegrityViolationException e) {
        Response response = Response.exception();
        e.printStackTrace();
        response.addErrorMsgToResponse(e.getMessage(), e);
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleDuplicateKey(ConstraintViolationException ex) {

        String messageKey = "org.hibernate.exception.ConstraintViolationException";
        String constraintName = messageSource.getMessage(ex.getConstraintName(), null, ex.getConstraintName(), Locale.getDefault());
        String message = messageSource.getMessage(messageKey, new Object[]{constraintName}, ex.getMessage(), Locale.getDefault());

        Response response = Response.exception();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(ClassCastException.class)
    public ResponseEntity<Object> classCastException(ClassCastException ex) {
        Response response = Response.exception();
        ex.printStackTrace();
        response.addErrorMsgToResponse(ex.getLocalizedMessage(), ex);
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<Object> missingHeaderRequest(MissingRequestHeaderException ex) {
        Response response = Response.exception();
        ex.printStackTrace();
        response.addErrorMsgToResponse(ex.getLocalizedMessage(), ex);
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

/*
    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<Object> missingPathVariableException(MissingPathVariableException ex) {
        RestErrorResponse restErrorResponse = new RestErrorResponse(HttpStatus.NOT_FOUND.value());
        restErrorResponse.setMessage(ex.getLocalizedMessage());
        return buildResponseEntity(restErrorResponse);
    }
*/


    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> fileNotFoundException(FileNotFoundException ex) {
        Response response = Response.exception();
        ex.printStackTrace();
        response.addErrorMsgToResponse(ex.getLocalizedMessage(), ex);
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> accessDeniedException(AccessDeniedException ex) {
        Response response = Response.exception();
        ex.printStackTrace();
        response.addErrorMsgToResponse(ex.getLocalizedMessage(), ex);
        return new ResponseEntity(response, HttpStatus.FORBIDDEN);

    }


    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<?> jsonProcessingException(JsonProcessingException ex) {
        Response response = Response.exception();
        ex.printStackTrace();
        response.addErrorMsgToResponse(ex.getLocalizedMessage(), ex);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<?> jsonProcessingException(JsonParseException ex) {
        Response response = Response.exception();
        ex.printStackTrace();
        response.addErrorMsgToResponse(ex.getLocalizedMessage(), ex);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }




}
