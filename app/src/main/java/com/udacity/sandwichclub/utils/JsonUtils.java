package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

public class JsonUtils {


    private static Sandwich mSandwich;


    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwichObject = new JSONObject(json);
            JSONObject sandwich = sandwichObject.getJSONObject("name");
            mSandwich.setAlsoKnownAs((List<String>) sandwich.getJSONArray("alsoKnownAs"));
            mSandwich.setDescription(sandwichObject.getJSONObject("description").toString());
            mSandwich.setIngredients((List<String>) sandwich.getJSONArray("ingredients"));
            mSandwich.setPlaceOfOrigin(sandwichObject.getJSONObject("placeOfOrigin").toString());
            mSandwich.setMainName(sandwich.getString("mainName"));
        }catch (JSONException e){}

        return mSandwich;
    }
}
