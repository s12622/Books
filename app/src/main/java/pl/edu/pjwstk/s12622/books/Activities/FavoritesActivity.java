package pl.edu.pjwstk.s12622.books.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import pl.edu.pjwstk.s12622.books.Interfaces.FavoriteBookListener;
import pl.edu.pjwstk.s12622.books.R;

public class FavoritesActivity extends AppCompatActivity implements FavoriteBookListener{

    TextView bookDetails;
    FragmentManager manager = this.getSupportFragmentManager();

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
        String [] descriptions = getResources().getStringArray(R.array.descriptions);
        bookDetails.setText(descriptions[index]);

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
