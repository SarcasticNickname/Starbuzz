package com.example.starbuzz.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.starbuzz.R;
import com.example.starbuzz.database.StarbuzzDatabaseHelper;
import com.example.starbuzz.drink.Drink;

public class DrinkCategoryActivity extends AppCompatActivity {

    private Cursor cursor;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);

        // Getting database helper
        SQLiteOpenHelper databaseHelper = new StarbuzzDatabaseHelper(this);
        // Finding listView
        ListView listView = findViewById(R.id.list_drinks);
        try {
            database = databaseHelper.getReadableDatabase();

            Toast toast3 = Toast.makeText(this, "Database is open: " + database.isOpen(), Toast.LENGTH_SHORT);
            toast3.show();

            cursor = database.query("DRINK",
                    new String[]{"_id", "NAME"},
                    null, null, null, null, null);
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[] {"NAME"},
                    new int[]{android.R.id.text1},
                    0);
            listView.setAdapter(adapter);
        } catch (SQLiteException ex) {
            Toast toast = Toast.makeText(this,"Database is unavailable", Toast.LENGTH_SHORT);
            toast.show();
            Toast toast2 = Toast.makeText(this, "Version " + database.getVersion(), Toast.LENGTH_SHORT);
            toast2.show();
        }

        // Setting listener
        AdapterView.OnItemClickListener onItemClickListener = (adapterView, itemView, position, id) -> {
            Intent intent = new Intent(DrinkCategoryActivity.this, DrinkActivity.class);
            intent.putExtra(DrinkActivity.EXTRA_DRINK, position + 1);
            startActivity(intent);
        };
        listView.setOnItemClickListener(onItemClickListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
        database.close();
    }
}