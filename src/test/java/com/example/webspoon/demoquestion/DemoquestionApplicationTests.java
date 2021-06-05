package com.example.webspoon.demoquestion;

import com.example.webspoon.demoquestion.pojo.AddRecipeRq;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //need this in Spring Boot test
public class DemoquestionApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    final ObjectMapper objectMapper = new ObjectMapper();

    private final String TEST_RECIPE_NAME = "Sea Food";
    private final String TEST_RECIPE_SNIPPET = "Crab and Shrimps";
    private final Integer TEST_RECIPE_EXPIRES_IN = 10;

    @BeforeEach
    public void shouldSaveReipe() throws Exception {
        final AddRecipeRq addRecipeRq = new AddRecipeRq();
        addRecipeRq.setExpiresIn(TEST_RECIPE_EXPIRES_IN);
        addRecipeRq.setName(TEST_RECIPE_NAME);
        addRecipeRq.setSnippet(TEST_RECIPE_SNIPPET);
        mockMvc.perform(
                post("/recipe")
                .content(objectMapper.writeValueAsString(addRecipeRq))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.name",is(addRecipeRq.getName())))
                .andExpect(jsonPath("$.expires_at",notNullValue()))
                .andExpect(jsonPath("$.url",notNullValue()));
    }

    @Test
    public void shouldGetRecipe() throws Exception {

        mockMvc.perform(
                get("/recipe")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        )
                .andDo(print())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.name",is(TEST_RECIPE_NAME)))
                .andExpect(jsonPath("$.expires_at",notNullValue()))
                .andExpect(jsonPath("$.url",notNullValue()));
    }
    @Test
    public void shouldLikeRecipe() throws Exception {

        mockMvc.perform(
                get("/recipe/like")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        )
                .andDo(print())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.name",is(TEST_RECIPE_NAME)))
                .andExpect(jsonPath("$.expires_at",notNullValue()))
                .andExpect(jsonPath("$.likes",greaterThan(0)))
                .andExpect(jsonPath("$.url",notNullValue()));
    }
}
