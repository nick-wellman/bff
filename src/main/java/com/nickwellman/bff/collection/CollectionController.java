package com.nickwellman.bff.collection;

import com.nickwellman.bff.collection.model.GasRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CollectionController {

    private final CollectionService service;


    public CollectionController(final CollectionService service) {
        this.service = service;
    }

    @PutMapping("/api/v1/gas")
    public Object addGas(@RequestBody final GasRequest request) {
        return service.addGas(request);
    }

    @GetMapping("/api/v1/gas")
    public Object getGas(@RequestParam final String vehicle) {
        return service.getGas(vehicle);
    }

    @GetMapping("/api/v1/recipe")
    public Object getAllRecipes() {
        return service.getAllRecipes();
    }

    @GetMapping("/api/v1/recipe/{id}")
    public Object getRecipe(@PathVariable final int id) {
        return service.getRecipeById(id);
    }

    @GetMapping("/api/v1/ingredient/{recipeId}")
    public Object getIngredientsByRecipeId(@PathVariable final int recipeId) {
        return service.getIngredientsByRecipeId(recipeId);
    }

    @GetMapping("/api/v1/direction/{recipeId}")
    public Object getDirectionsByRecipeId(@PathVariable final int recipeId) {
        return service.getDirectionsForRecipeId(recipeId);
    }

    @GetMapping("/api/v1/tag/{recipeId}")
    public Object getTagsByRecipeId(@PathVariable final int recipeId) {
        return service.getTagByRecipeId(recipeId);
    }
}
