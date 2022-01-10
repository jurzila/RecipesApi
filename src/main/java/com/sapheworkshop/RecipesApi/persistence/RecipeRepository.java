package com.sapheworkshop.RecipesApi.persistence;

import com.sapheworkshop.RecipesApi.business.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

    List<Recipe> findRecipeByNameContainingIgnoreCaseOrderByDateDesc(String name);

    List<Recipe> findAllByCategoryIgnoreCaseOrderByDateDesc(String category);

}
