package pl.edu.pjwstk.s12622.books.Activities;

import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import pl.edu.pjwstk.s12622.books.Database.ExampleDBHelper;
import pl.edu.pjwstk.s12622.books.Interfaces.FavoriteBookListener;
import pl.edu.pjwstk.s12622.books.R;

public class FavoritesActivity extends AppCompatActivity implements FavoriteBookListener{

    TextView bookDetails;
    FragmentManager manager = this.getSupportFragmentManager();
    ExampleDBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        bookDetails = (TextView) findViewById(R.id.bookDetails);

        if(findViewById(R.id.layout_default) != null)
        {
            manager.beginTransaction()
                    .hide(manager.findFragmentById(R.id.detailsFragment))
                    .show(manager.findFragmentById(R.id.listFragment))
                    .commit();
        }

        if(findViewById(R.id.layout_landscape) != null)
        {
            manager.beginTransaction()
                    .show(manager.findFragmentById(R.id.listFragment))
                    .show(manager.findFragmentById(R.id.detailsFragment))
                    .commit();
        }

    }

    @Override
    public void onFavoriteBookSelected(int index) {

        dbHelper = new ExampleDBHelper(getApplicationContext());

        final Cursor cursor = dbHelper.getAllBooks();

        ArrayList<String> descriptionList = new ArrayList<String>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            descriptionList.add(cursor.getString(cursor.getColumnIndex("description"))); //add the item
            cursor.moveToNext();
        };
        bookDetails.setText(descriptionList.get(index));

        if(findViewById(R.id.layout_default) != null)
        {
            manager.beginTransaction()
                    .hide(manager.findFragmentById(R.id.listFragment))
                    .show(manager.findFragmentById(R.id.detailsFragment))
                    .addToBackStack(null)
                    .commit();
        }
    }

}
