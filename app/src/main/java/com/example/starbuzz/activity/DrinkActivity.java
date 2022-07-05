package com.example.starbuzz.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.starbuzz.R;
import com.example.starbuzz.database.StarbuzzDatabaseHelper;
import com.example.starbuzz.drink.Drink;

public class DrinkActivity extends AppCompatActivity {
    public static final String EXTRA_DRINK = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        // Drink id
        int drinkId = (Integer)getIntent().getExtras().get(EXTRA_DRINK);

        // Getting database helper
        SQLiteOpenHelper starbuzzDataHelper = new StarbuzzDatabaseHelper(this);
        // Getting cursor
        Cursor cursor;
        try {
            SQLiteDatabase database = starbuzzDataHelper.getReadableDatabase();
            cursor = database.query("DRINK",
                    new String [] {"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    "_id = ?",
                    new String[] {Integer.toString(drinkId)}, null, null, null);
            if (cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);

                // Setting photo
                ImageView photoView = findViewById(R.id.photo);
                photoView.setImageResource(photoId);
                photoView.setContentDescription(nameText);
                // Setting name
                TextView name = findViewById(R.id.name);
                name.setText(nameText);
                // Setting description
                TextView description = findViewById(R.id.description);
                description.setText(descriptionText);

                cursor.close();
                database.close();
            }
        } catch (SQLiteException ex) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}