package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private  static List<String> JsonToStringList(JSONArray array) throws JSONException {
        List<String> stringList = new ArrayList<>();
        int index;
        for(index = 0; index < array.length(); index++) {
            stringList.add(array.getString(index));
        }

        return stringList;
    }

    public static Sandwich parseSandwichJson(String json) throws JSONException {

        // Sandwich JSON field names
        final String SANDWICH_NAME = "name";
        final String SANDWICH_MAINNAME = "mainName";
        final String SANDWICH_ALSOKNOWNAS = "alsoKnownAs";
        final String SANDWICH_ORIGIN = "placeOfOrigin";
        final String SANDWICH_DESCRIPTION = "description";
        final String SANDWICH_IMAGE = "image";
        final String SANDWICH_INGREDIENTS = "ingredients";

        // Convert string to JSON
        JSONObject sandwichJson = new JSONObject(json);

        // Get the sandwich names
        JSONObject nameJson = sandwichJson.getJSONObject(SANDWICH_NAME);
        String mainName = nameJson.getString(SANDWICH_MAINNAME);
        JSONArray alsoKnowAsJson = nameJson.getJSONArray(SANDWICH_ALSOKNOWNAS);
        List<String> alsoKnownAs = JsonToStringList(alsoKnowAsJson);

        // Get the place of origin
        String placeOfOrigin = sandwichJson.getString(SANDWICH_ORIGIN);

        // Get the description
        String description = sandwichJson.getString(SANDWICH_DESCRIPTION);

        // Get the image URL
        String imageUrl = sandwichJson.getString(SANDWICH_IMAGE);

        // Get the list of ingredients
        JSONArray ingredientsJson = (JSONArray) sandwichJson.getJSONArray(SANDWICH_INGREDIENTS);
        List<String> ingredients = JsonToStringList(ingredientsJson);

        // Create the Sandwich Object and return it
        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, imageUrl, ingredients);
    }
}
