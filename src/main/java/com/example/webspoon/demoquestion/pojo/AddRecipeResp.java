package com.example.webspoon.demoquestion.pojo;

import com.example.webspoon.demoquestion.model.Recipe;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddRecipeResp {
    String url;
    String name;
    @JsonProperty("expires_at")
    //2020-02-22T20:02:32Z
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    Date expiresAt;
    String snippet;
    Integer likes;


    public AddRecipeResp(Recipe recipe,String url, String recipePath){
        this.url  = String.format("%s/%s",url,recipePath);
        this.name = recipe.getName();
        this.likes = recipe.getLikes();
        this.snippet = recipe.getName();
        this.expiresAt = recipe.getExpiresAt();
    }

}
