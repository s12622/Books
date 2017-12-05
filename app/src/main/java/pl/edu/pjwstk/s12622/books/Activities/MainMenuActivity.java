package pl.edu.pjwstk.s12622.books.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pl.edu.pjwstk.s12622.books.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button books = (Button) findViewById(R.id.button);
        Button favorites = (Button) findViewById(R.id.button2);
        Button exit = (Button) findViewById(R.id.button3);

        favorites.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainMenuActivity.this, FavoritesActivity.class);
                MainMenuActivity.this.startActivity(myIntent);
            }
        });


        books.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainMenuActivity.this, SearchActivity.class);
                MainMenuActivity.this.startActivity(myIntent);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });    }
}
