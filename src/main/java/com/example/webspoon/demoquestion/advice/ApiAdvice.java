package com.example.webspoon.demoquestion.advice;

import com.example.webspoon.demoquestion.exception.RecipeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@RestControllerAdvice
@Slf4j
public class ApiAdvice {

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

        return "unexpected error processing request. Please try again later or contact help desk";
    }
}
