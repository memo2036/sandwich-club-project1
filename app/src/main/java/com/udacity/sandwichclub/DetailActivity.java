package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView originTextView, alsoKnownTextViw, ingredientsTextView, description;
    List<String> alsoKnwonAsStr, ingredientsStr;
    String descriptionStr,originPlaceStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        originTextView = findViewById(R.id.origin_tv);
        alsoKnownTextViw = findViewById(R.id.also_known_tv);
        ingredientsTextView = findViewById(R.id.ingredients_tv);
        description = findViewById(R.id.description_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        alsoKnwonAsStr = sandwich.getAlsoKnownAs();
        descriptionStr = sandwich.getDescription();
        ingredientsStr = sandwich.getIngredients();
        originPlaceStr = sandwich.getPlaceOfOrigin();

        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.drawable.user_placeholder)
                .error(R.drawable.user_placeholder_error)
                .into(ingredientsIv);

        populateUI();
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        originTextView.setText(originPlaceStr);
        alsoKnownTextViw.setText(alsoKnwonAsStr.toString());
        ingredientsTextView.setText(ingredientsStr.toString());
        description.setText(descriptionStr);
    }
}
