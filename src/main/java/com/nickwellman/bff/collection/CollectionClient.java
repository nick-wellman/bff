package com.nickwellman.bff.collection;

import com.nickwellman.bff.collection.model.GasRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "collection-client", url = "${collection-service.url}")
public interface CollectionClient {

    @PutMapping(value = "/api/v1/gas", headers = {""})
    Object addGas(@RequestBody GasRequest request);

    @GetMapping(value = "/api/v1/gas", headers = {""})
    Object getGas(@RequestParam String vehicle);

    @GetMapping(value = "/api/v1/recipe", headers = {""})
    Object getAllRecipes();

    @GetMapping(value = "/api/v1/recipe/{id}", headers = {""})
    Object getRecipe(@PathVariable("id") int id);

    @GetMapping(value = "/api/v1/ingredients/{recipeId}", headers = {""})
    Object getIngredientsForRecipeId(@PathVariable("recipeId") int recipeId);

    @GetMapping(value = "/api/v1/direction/{recipeId}", headers = {""})
    Object getDirectionsForRecipeId(@PathVariable("recipeId") int recipeId);

    @GetMapping(value = "/api/v1/tag/{recipeId}", headers = {""})
    Object getTagsForRecipeId(@PathVariable("recipeId") int recipeId);
}
