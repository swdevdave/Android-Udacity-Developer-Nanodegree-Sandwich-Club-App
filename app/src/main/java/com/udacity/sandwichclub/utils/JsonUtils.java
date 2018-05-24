package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        //create new sandwich object.
        Sandwich sandwich = null;

        try {

            // Creating new Json object
            JSONObject sandwichJsonObject = new JSONObject(json);

            //parse into name object
            JSONObject sandwichName = sandwichJsonObject.getJSONObject("name");
            //get mainName string from inside name object
            String mainName = sandwichName.getString("mainName");
            // get alsoKnownAs array from inside name object
            JSONArray akaJsonArray = sandwichName.getJSONArray("alsoKnownAs");
            List<String> aka = jsonArrayToStringList(akaJsonArray);

            // parse placeOfOrigin
            String placeOfOrigin =  sandwichJsonObject.getString("placeOfOrigin");
            // parse description
            String description =  sandwichJsonObject.getString("description");
            // parse image url
            String image =  sandwichJsonObject.getString("image");

            // parse ingredients array
            JSONArray ingredientsJsonArray = sandwichJsonObject.getJSONArray("ingredients");
            List<String> ingredients = jsonArrayToStringList(ingredientsJsonArray);

            //create new sandwich with parsed data from above
            sandwich = new Sandwich(mainName, aka, placeOfOrigin, description, image, ingredients);

        // if 'try' fails throw error
        }catch (JSONException e) {
            e.printStackTrace();
        }
        // return sandwich contents
        return sandwich;
    }


    // Constructor to iterate though json array converting it to a string.
    private static List<String> jsonArrayToStringList(JSONArray jsonArray) throws JSONException {

        int arrayLength = jsonArray.length();
        List<String> result = new ArrayList<>();

        // basic for loop based on array length.
        for (int i = 0; i < arrayLength; i++) {
            result.add(jsonArray.getString(i));
        }

        return result;
    }
}
