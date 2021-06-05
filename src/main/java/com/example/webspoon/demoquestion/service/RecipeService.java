package com.example.webspoon.demoquestion.service;

import com.example.webspoon.demoquestion.model.Recipe;
import com.example.webspoon.demoquestion.pojo.AddRecipeRq;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Service
public class RecipeService {

    Recipe recipe = null;

    public void updateRecipe(@NonNull Recipe updatedRecipe){
            this.recipe = updatedRecipe;
    }
    /*
    * Service class method to save recipe in memory
    * */
    public Recipe saveRecipe(@NonNull AddRecipeRq addRecipeRq){

        //what happens if it exist.. not part of requirement
        recipe  = new Recipe();
        recipe.setName(addRecipeRq.getName());
        recipe.setSnippet(addRecipeRq.getSnippet());
        recipe.setExpiresAt(new Date( System.currentTimeMillis() + 1000L*addRecipeRq.getExpiresIn()));
        recipe.setCreatedAt(new Date( System.currentTimeMillis()));



        return recipe;
    }

    /*
     * Service class method to get recipe from memory
     * */
    public Recipe getRecipe(){

        return recipe;
    }
}
