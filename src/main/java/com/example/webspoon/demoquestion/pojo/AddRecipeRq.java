package com.example.webspoon.demoquestion.pojo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddRecipeRq {
    @NotBlank
    String name;
    @NotNull
    @JsonProperty("expires_in")
    Integer expiresIn;
    @NotBlank
    String snippet;
}
