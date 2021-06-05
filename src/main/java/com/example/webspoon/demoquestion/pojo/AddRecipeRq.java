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
    @NotBlank(message = "Recipe Name is compulsory.")
    String name;
    @NotNull(message = "Include expires in (seconds)")
    @JsonProperty("expires_in")
    Integer expiresIn;
    @NotBlank(message = "Please include a snippet")
    String snippet;
}
