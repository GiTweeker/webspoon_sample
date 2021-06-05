package com.example.webspoon.demoquestion.advice;

import com.example.webspoon.demoquestion.exception.RecipeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ApiAdvice {
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    List<ObjectError> handleMethodArgumentException(MethodArgumentNotValidException e, final Model model, HttpServletResponse response) {
        log.error("Exception during execution of application "+ e.getMessage());
        // List<ObjectError>  restPojo = new RestResponsePojo<>();
        log.info(e.getLocalizedMessage());
        BeanPropertyBindingResult beanPropertyBindingResult = (BeanPropertyBindingResult) e.getBindingResult();
        List<ObjectError> objectErrors = beanPropertyBindingResult.getAllErrors();
/*        String errorMessage = "Unable to process request. Validation error(s): ".concat(objectErrors
                .stream()
                .map(ObjectError::getDefaultMessage)
                .filter(m -> StringUtils.hasText(m) && !m.toLowerCase().contains("javax".toLowerCase()))
                .collect(Collectors.joining("; ")));*/


        return objectErrors;
    }
    @ExceptionHandler({RecipeNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    String handleRecipeNotFoundException(RecipeNotFoundException e, final Model model, HttpServletResponse response) {
        log.error("Exception during execution of application "+ e.getMessage());

        return e.getMessage();
    }
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    String handleGeneralException(Exception e, final Model model, HttpServletResponse response) {
        log.error("Exception during execution of application "+ e.getMessage());
      e.printStackTrace();
        return "unexpected error processing request. Please try again later or contact help desk";
    }
}
