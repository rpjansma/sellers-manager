package com.sellers.manager.userinterface.handler;

import br.com.bradescoseguros.opin.application.dto.ErrorDetailDTO;
import br.com.bradescoseguros.opin.application.dto.ErrorDetailDTO.ErrorDetailDTOBuilder;
import br.com.bradescoseguros.opin.application.enums.ErrorType;
import br.com.bradescoseguros.opin.userinterface.exception.*;
import feign.FeignException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestControllerAdviceHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignException(FeignException ex, WebRequest request) {
        HttpStatus status = HttpStatus.valueOf(ex.status());
        ErrorType errorType = ErrorType.BAD_REQUEST;
        String detail = "Usuário não existe";
        ErrorDetailDTO apiError = createApiErrorBuilder(status, errorType, detail)
                .detail(detail)
                .build();
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> handleForbiddenException(ForbiddenException ex, WebRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        ErrorType errorType = ErrorType.FORBIDDEN;
        String detail = ex.getMessage();
        ErrorDetailDTO apiError = createApiErrorBuilder(status, errorType, detail)
                .detail(detail)
                .build();
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErrorType errorType = ErrorType.UNAUTHORIZED;
        String detail = ex.getMessage();
        ErrorDetailDTO apiError = createApiErrorBuilder(status, errorType, detail)
                .detail(detail)
                .build();
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnexpectedException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorType errorType = ErrorType.INTERNAL_SERVER_ERROR;
        String detail = "Um erro inesperado aconteceu. Entre em contato com o administrador do sistema.";
        ErrorDetailDTO apiError = createApiErrorBuilder(status, errorType, detail)
                .detail(detail)
                .build();
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorType errorType = ErrorType.NOT_FOUND;
        String detail = ex.getMessage();
        ErrorDetailDTO apiError = createApiErrorBuilder(status, errorType, detail)
                .detail(detail)
                .build();
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Object> handleInternalServerError(InternalServerErrorException ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorType errorType = ErrorType.INTERNAL_SERVER_ERROR;
        String detail = "Erro no processamento do servidor";
        ErrorDetailDTO apiError = createApiErrorBuilder(status, errorType, detail)
                .detail(detail)
                .build();
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ExternalServiceProcessingError.class)
    public ResponseEntity<Object> handleExternalServiceProcessingError(ExternalServiceProcessingError ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_GATEWAY;
        ErrorType errorType = ErrorType.BAD_GATEWAY;
        String detail = "Erro na conexão com API externa";
        ErrorDetailDTO apiError = createApiErrorBuilder(status, errorType, detail)
                .detail(detail)
                .build();
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler({BadRequestException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleDatabaseCreateException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorType errorType = ErrorType.BAD_REQUEST;
        String detail = ex.getMessage();
        ErrorDetailDTO apiError = createApiErrorBuilder(status, errorType, detail)
                .detail(detail)
                .build();
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(DataBaseCreateException.class)
    public ResponseEntity<Map<String, Object>> handleDatabaseCreateRetException(DataBaseCreateException e) {
        Map<String, Object> errorInfo = new LinkedHashMap<>();
        errorInfo.put("title", HttpStatus.BAD_REQUEST.toString());
        errorInfo.put("requestDateTime", new Date());
        errorInfo.put("details", e.getMessage());
        errorInfo.put("object", e.getObjDataBase());
        errorInfo.put("code", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<Object> handlePageLimitException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErrorType errorType = ErrorType.UNPROCESSABLE_ENTITY;
        String detail = ex.getMessage();
        ErrorDetailDTO apiError = createApiErrorBuilder(status, errorType, detail)
                .detail(detail)
                .build();
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorType errorType = ErrorType.BAD_REQUEST;
        String detail;
        if (ex.getConstraintViolations().isEmpty()) {
            detail = ex.getMessage();
        } else {
            detail = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessageTemplate).collect(Collectors.joining("; "));
        }
        ErrorDetailDTO apiError = createApiErrorBuilder(status, errorType, detail)
                .detail(detail)
                .build();
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorType errorType = ErrorType.BAD_REQUEST;
        String detail = ex.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining("; "));
        ErrorDetailDTO apiError = createApiErrorBuilder(status, errorType, detail)
                .detail(detail)
                .build();
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    private ErrorDetailDTOBuilder createApiErrorBuilder(HttpStatus status, ErrorType errorType, String detail) {
        return ErrorDetailDTO.builder()
                .requestDateTime(LocalDateTime.now())
                .code(status.value())
                .title(errorType.getTitle())
                .detail(detail);
    }
}