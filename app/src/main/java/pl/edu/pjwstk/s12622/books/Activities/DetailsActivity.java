package pl.edu.pjwstk.s12622.books.Activities;

import android.content.Context;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pjwstk.s12622.books.R;

public class DetailsActivity extends AppCompatActivity {

    private TextView titleText, publisherText, descriptionText, authorText;
    private ImageView bookThumbnail;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        context = getApplicationContext();
        titleText = (TextView)findViewById(R.id.bookTitle);
        publisherText = (TextView)findViewById(R.id.bookPublisher);
        descriptionText = (TextView)findViewById(R.id.bookDescription);
        bookThumbnail = (ImageView)findViewById(R.id.bookThumbnail);
        authorText = (TextView)findViewById(R.id.bookAuthor);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            try {
                Picasso.with(context).load(extras.getString("thumbnail")).fit().into(bookThumbnail);
                titleText.setText(extras.getString("title"));
                authorText.setText(extras.getString("author"));
                publisherText.setText(extras.getString("publisher"));
                descriptionText.setText(extras.getString("description"));


                Log.d("myTag", extras.getString("title"));
                Log.d("myTag", extras.getString("author"));
                Log.d("myTag", extras.getString("publisher"));
                Log.d("myTag", extras.getString("description"));
                Log.d("myTag", extras.getString("thumbnail"));


            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
