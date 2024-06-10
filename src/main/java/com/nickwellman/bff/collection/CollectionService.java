package com.nickwellman.bff.collection;

import com.nickwellman.bff.collection.model.GasRequest;
import org.springframework.stereotype.Service;

@Service
public class CollectionService {

    private final CollectionClient client;

    public CollectionService(final CollectionClient client) {
        this.client = client;
    }


    public Object addGas(final GasRequest request) {
        return client.addGas(request);
    }

    public Object getGas(final String vehicle) {
        return client.getGas(vehicle);
    }

    public Object getAllRecipes() {
        return client.getAllRecipes();
    }

    public Object getRecipeById(final int id) {
        return client.getRecipe(id);
    }

    public Object getIngredientsByRecipeId(final int recipeId) {
        return client.getIngredientsForRecipeId(recipeId);
    }

    public Object getTagByRecipeId(final int recipeId) {
        return client.getTagsForRecipeId(recipeId);
    }

    public Object getDirectionsForRecipeId(final int recipeId) {
        return client.getDirectionsForRecipeId(recipeId);
    }
}
