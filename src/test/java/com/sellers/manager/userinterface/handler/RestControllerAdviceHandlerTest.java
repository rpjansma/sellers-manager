package com.sellers.manager.userinterface.handler;

import com.sellers.manager.application.dto.ErrorDetailDTO;
import com.sellers.manager.application.enums.ErrorType;
import com.sellers.manager.userinterface.exception.BadRequestException;
import com.sellers.manager.userinterface.exception.InternalServerErrorException;
import com.sellers.manager.userinterface.exception.UnprocessableEntityException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hibernate.validator.internal.engine.ConstraintViolationImpl.forBeanValidation;
import static org.mockito.Mockito.mock;

public class RestControllerAdviceHandlerTest {

    public static final String GENERIC_ERROR_MESSAGE = "Um erro inesperado aconteceu.";
    public static final String GENERIC_ERROR_MESSAGE_INTERNAL_ERROR = "Erro no processamento do servidor";
    private final RestControllerAdviceHandler advice;
    @Mock
    private WebRequest webRequest;

    public RestControllerAdviceHandlerTest() {
        MockitoAnnotations.openMocks(this);
        advice = new RestControllerAdviceHandler();
    }

    @Tag("unit")
    @Test
    @DisplayName("Must handle handleInternalServerError")
    void handleInternalServerError() {
        InternalServerErrorException ex = new InternalServerErrorException(GENERIC_ERROR_MESSAGE_INTERNAL_ERROR);
        ResponseEntity<Object> response = advice.handleInternalServerError(ex, webRequest);
        testHandleException(response, HttpStatus.INTERNAL_SERVER_ERROR, GENERIC_ERROR_MESSAGE_INTERNAL_ERROR, ErrorType.INTERNAL_SERVER_ERROR);
    }

    @Tag("unit")
    @Test
    @DisplayName("Must handle NotFoundException")
    void handleNotFoundTest() {
        RuntimeException ex = new RuntimeException(GENERIC_ERROR_MESSAGE);
        ResponseEntity<Object> response = advice.handleNotFound(ex, webRequest);
        testHandleException(response, HttpStatus.NOT_FOUND, GENERIC_ERROR_MESSAGE, ErrorType.NOT_FOUND);
    }

    @Tag("unit")
    @Test
    @DisplayName("Must handle BadRequestException")
    void handleBadRequestException() {
        BadRequestException ex = new BadRequestException(GENERIC_ERROR_MESSAGE);
        ResponseEntity<Object> response = advice.handleDatabaseCreateException(ex, webRequest);
        testHandleException(response, HttpStatus.BAD_REQUEST, GENERIC_ERROR_MESSAGE, ErrorType.BAD_REQUEST);
    }


    @Tag("unit")
    @Test
    @DisplayName("Must handle UnprocessableEntityException")
    void handleUnprocessableEntityException() {
        UnprocessableEntityException ex = new UnprocessableEntityException(GENERIC_ERROR_MESSAGE);
        ResponseEntity<Object> response = advice.handlePageLimitException(ex, webRequest);
        testHandleException(response, HttpStatus.UNPROCESSABLE_ENTITY, GENERIC_ERROR_MESSAGE, ErrorType.UNPROCESSABLE_ENTITY);
    }

    @Tag("unit")
    @Test
    @DisplayName("Must handle ConstraintViolationException")
    void handleConstraintViolationException() {
        HashSet<ConstraintViolation<String>> constraintsViolations = new HashSet<>();
        ConstraintViolation<String> ConstraintViolationImpl = forBeanValidation(GENERIC_ERROR_MESSAGE, null, null, null, null, null, null, null, null, null, null);
        constraintsViolations.add(ConstraintViolationImpl);
        ConstraintViolationException ex = new ConstraintViolationException(GENERIC_ERROR_MESSAGE, constraintsViolations);
        ResponseEntity<Object> response = advice.handleConstraintViolationException(ex, webRequest);
        testHandleException(response, HttpStatus.BAD_REQUEST, GENERIC_ERROR_MESSAGE, ErrorType.BAD_REQUEST);
    }

    @Tag("unit")
    @Test
    @DisplayName("Must handle ConstraintViolationException with out ConstraintViolations")
    void handleConstraintViolationExceptionWithOutConstraintViolations() {
        ConstraintViolationException ex = new ConstraintViolationException(GENERIC_ERROR_MESSAGE, new HashSet<>());
        ResponseEntity<Object> response = advice.handleConstraintViolationException(ex, webRequest);
        testHandleException(response, HttpStatus.BAD_REQUEST, GENERIC_ERROR_MESSAGE, ErrorType.BAD_REQUEST);
    }

    @Tag("unit")
    @Test
    @DisplayName("Must handle MethodArgumentNotValidException")
    void handleMethodArgumentNotValidException() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        ResponseEntity<Object> response = advice.handleMethodArgumentNotValid(ex, null, HttpStatus.BAD_REQUEST, null);
        testHandleException(response, HttpStatus.BAD_REQUEST, "", ErrorType.BAD_REQUEST);
    }

    private void testHandleException(ResponseEntity<Object> response, HttpStatus httpStatus, String detailMessage, ErrorType errorType) {
        ErrorDetailDTO errorDetail = (ErrorDetailDTO) response.getBody();
        assertThat(errorDetail, Matchers.notNullValue());
        assertThat(response.getStatusCode(), Matchers.equalTo(httpStatus));
        assertThat(errorDetail.getTitle(), is(errorType.getTitle()));
        assertThat(errorDetail.getDetail(), is(detailMessage));
    }
}
