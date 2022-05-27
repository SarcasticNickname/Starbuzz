package com.example.starbuzz.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.starbuzz.R;
import com.example.starbuzz.drink.Drink;

public class DrinkActivity extends AppCompatActivity {
    public static final String EXTRA_DRINK = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        // Getting info about drink
        int drinkId = (Integer)getIntent().getExtras().get(EXTRA_DRINK);
        Drink drink = Drink.drinks[drinkId];
        // Setting photo
        ImageView photoView = findViewById(R.id.photo);
        photoView.setImageResource(drink.getImageResourceId());
        photoView.setContentDescription(drink.toString());
        // Setting name
        TextView name = findViewById(R.id.name);
        name.setText(drink.getName());
        // Setting description
        TextView description = findViewById(R.id.description);
        description.setText(drink.getDescription());
    }
}