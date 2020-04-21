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


    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwichObject = new JSONObject(json);
            JSONObject sandwich = sandwichObject.getJSONObject("name");

            List<String> knwonAsList = convertJsonArrayToStringList(sandwich.getJSONArray("alsoKnownAs"));
            List<String> ingrdList = convertJsonArrayToStringList(sandwichObject.getJSONArray("ingredients"));

            mSandwich.setMainName(sandwich.getString("mainName"));
            mSandwich.setDescription(sandwichObject.getString("description"));
            mSandwich.setPlaceOfOrigin(sandwichObject.getString("placeOfOrigin"));
            mSandwich.setImage(sandwichObject.getString("image"));
            mSandwich.setAlsoKnownAs(knwonAsList);
            mSandwich.setIngredients(ingrdList);
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
