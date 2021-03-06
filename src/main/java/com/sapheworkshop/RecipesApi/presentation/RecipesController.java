package com.sapheworkshop.RecipesApi.presentation;

import com.sapheworkshop.RecipesApi.business.Recipe;
import com.sapheworkshop.RecipesApi.business.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipe")
public class RecipesController {

    @Autowired
    RecipeService recipeService;

    // Putting in the Json object and receiving autogenerated id for recipe;
    @PostMapping("/new")
    public ResponseEntity<Map<String, Integer>> postRecipe(@Valid @RequestBody Recipe recipe) {
        return new ResponseEntity<>(Map.of("id", recipeService.save(recipe)), HttpStatus.OK);
    }

    // Looks for recipe by id, if recipe does not exist gives response of NOT_FOUND
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Integer id) throws ResponseStatusException {
            return new ResponseEntity<>(recipeService.getRecipe(id), HttpStatus.OK);
    }


    // Deletes recipe by id, if recipe does not exist gives response of NOT_FOUND
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable Integer id) {
        if(!recipeService.delete(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    // Looks for recipe by given parameter
    @GetMapping("/search")
    public List<Recipe> getRecipesByParam(@RequestParam Map<String, String> params) {
        if(params.containsKey("category")) {
            return recipeService.findRecipeByCategory(params.get("category"));
        }
        if(params.containsKey("name"))
            return recipeService.findRecipeByName(params.get("name"));
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    // Updates recipe by id, if recipe does not exist gives response of NOT_FOUND
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecipe(@PathVariable Integer id, @Valid @RequestBody Recipe recipe) {
        recipeService.update(id, recipe);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
