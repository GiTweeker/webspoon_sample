package com.example.webspoon.demoquestion.endpoint;

import com.example.webspoon.demoquestion.exception.RecipeNotFoundException;
import com.example.webspoon.demoquestion.model.Recipe;
import com.example.webspoon.demoquestion.pojo.AddRecipeResp;
import com.example.webspoon.demoquestion.pojo.AddRecipeRq;
import com.example.webspoon.demoquestion.service.RecipeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("recipe")
@RestController
public class RecipeEndpoint {

    private final RecipeService recipeService;
    private final String RECIPE_PATH = "recipe";

    @Value("${app.snippets.recipe.expiration.added.seconds:30}")
    Integer defaultTimeForExpiration;


    public RecipeEndpoint(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    /*
     * Request Method to handle adding recipe
     * */

    @RequestMapping(value = "", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus( HttpStatus.CREATED )
    public AddRecipeResp addRecipe(@Valid @RequestBody AddRecipeRq addRecipeRq,HttpServletRequest req){

        final Recipe recipe = recipeService.saveRecipe(addRecipeRq);


        return new AddRecipeResp(recipe, getBaseUrl(req),RECIPE_PATH);



    }

    /*
     * Request Method to handle get recipe
     * */

    @RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus( HttpStatus.OK )
    public AddRecipeResp getRecipe(HttpServletRequest req) throws Exception {

        return getAddRecipeResp(req,false);


    }
    /*
     * Request Method to handle like of recipe (Increment Like Count)
     * */

    @RequestMapping(value = "like", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus( HttpStatus.OK )
    public AddRecipeResp likeRecipe(HttpServletRequest req) throws Exception {

        return getAddRecipeResp(req,true);


    }

    /*
    * Base Method for adding recipe in Controller (Extracted)
    * Allows caller to increment like count
    * */
    private AddRecipeResp getAddRecipeResp(HttpServletRequest req,boolean likeRecipe) throws RecipeNotFoundException {
        final Recipe recipe = recipeService.getRecipe();

        if(!ObjectUtils.isEmpty(recipe) && recipe.getExpiresAt().getTime() > System.currentTimeMillis()){

            recipe.increaseExpiresTimeBy(defaultTimeForExpiration);
            if(likeRecipe){
                recipe.like();
            }

            return new AddRecipeResp(recipe, getBaseUrl(req),RECIPE_PATH);
        }


        throw new RecipeNotFoundException("Recipe Not Found");
    }

    private String getBaseUrl(HttpServletRequest req) {
        return req.getScheme() + "://" + req.getServerName()
                + (req.getServerPort() == 80 ? "" : ":" + req.getServerPort()  )+ req.getContextPath();
    }
}
