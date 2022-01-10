package com.sapheworkshop.RecipesApi.business;

import com.sapheworkshop.RecipesApi.persistence.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe getRecipe(Integer id) throws ResponseStatusException {
        Optional<Recipe> foundRecipe = recipeRepository.findById(id);
        if (foundRecipe.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return foundRecipe.get();
    }

    public Integer save(Recipe recipeToSave) {
        Recipe createdRecipe = recipeRepository.save(recipeToSave);
        return createdRecipe.getId();
    }

    public boolean delete(Integer id) {
        if (recipeRepository.existsById(id)) {
            recipeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Recipe> findRecipeByName(String name) {
        return recipeRepository.findRecipeByNameContainingIgnoreCaseOrderByDateDesc(name);
    }

    public List<Recipe> findRecipeByCategory(String category){
        return recipeRepository.findAllByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public void update(Integer id, Recipe recipe) {
        if(!recipeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        recipe.setId(id);
        recipeRepository.save(recipe);
    }
}
