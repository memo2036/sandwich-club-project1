package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static Sandwich mSandwich = new Sandwich();

    private static final String name = "name";
    private static final String alsoKnownAs = "alsoKnownAs";
    private static final String ingredients = "ingredients";
    private static final String mainName = "mainName";
    private static final String description = "description";
    private static final String placeOfOrigin = "placeOfOrigin";
    private static final String image = "image";

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwichObject = new JSONObject(json);
            JSONObject sandwich = sandwichObject.optJSONObject(name);

            List<String> knownAsList = convertJsonArrayToStringList(sandwich.optJSONArray(alsoKnownAs));
            List<String> ingredList = convertJsonArrayToStringList(sandwichObject.optJSONArray(ingredients));

            mSandwich.setMainName(sandwich.optString(mainName));
            mSandwich.setDescription(sandwichObject.optString(description));
            mSandwich.setPlaceOfOrigin(sandwichObject.optString(placeOfOrigin));
            mSandwich.setImage(sandwichObject.optString(image));
            mSandwich.setAlsoKnownAs(knownAsList);
            mSandwich.setIngredients(ingredList);
        }catch (JSONException e){ e.printStackTrace();}

        return mSandwich;
    }

    //This method were found in the knowledge hub
    private static List<String> convertJsonArrayToStringList(JSONArray jsonArray)
            throws JSONException {
        List<String> result = new ArrayList<>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            result.add(jsonArray.getString(i));
        }
        return result;
    }
}
