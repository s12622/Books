package pl.edu.pjwstk.s12622.books.Activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import pl.edu.pjwstk.s12622.books.Adapters.BookAdapter;
import pl.edu.pjwstk.s12622.books.Classes.CheckNetwork;
import pl.edu.pjwstk.s12622.books.Database.ExampleDBHelper;
import pl.edu.pjwstk.s12622.books.Models.Book;
import pl.edu.pjwstk.s12622.books.Models.BookResponse;
import pl.edu.pjwstk.s12622.books.R;

public class SearchActivity extends AppCompatActivity{
    private SearchView searchBar;
    private Context context;
    private RequestQueue queue;
    private ArrayList<Book> booksList = new ArrayList<>();
    private ListView listView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(CheckNetwork.isInternetAvailable(SearchActivity.this)) {
            setContentView(R.layout.activity_books);
        }
        else
        {
            Intent i = new Intent(SearchActivity.this, InternetConnectionActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }

        searchBar = (SearchView)findViewById(R.id.searchBar);
        context = getApplicationContext();
        listView = (ListView)findViewById(R.id.booksListView) ;
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        queue = Volley.newRequestQueue(context);

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressBar.setVisibility(View.VISIBLE);
                getBooksList(query);
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(SearchActivity.this, DetailsActivity.class);

                i.putExtra("title", booksList.get(position).volumeInfo.title);
                if (booksList.get(position).volumeInfo.authors != null)
                    i.putExtra("author", booksList.get(position).volumeInfo.authors.get(0));
                i.putExtra("publisher", booksList.get(position).volumeInfo.publisher);
                if (booksList.get(position).volumeInfo.imageLinks != null)
                        {
                            i.putExtra("thumbnail", booksList.get(position).volumeInfo.imageLinks.thumbnail);
                        }
                if (booksList.get(position).volumeInfo.imageLinks == null)
                        {
                            i.putExtra("thumbnail", getString(R.string.default_thumbnail));
                        }
                i.putExtra("description", booksList.get(position).volumeInfo.description);

                startActivity(i);
            }
        });


    }

    private void getBooksList(final String q) {
        booksList.clear();
        StringRequest stringRequest = new StringRequest
                (Request.Method.GET, getString(R.string.api_query) + q+"&maxResults=10",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                    Gson gson = new GsonBuilder()
                                            .create();
                                    BookResponse bookResponse = gson.fromJson(response,  BookResponse.class);
                                    booksList.addAll(bookResponse.items);

                                    BookAdapter adapter = new BookAdapter(getApplicationContext(), R.layout.itemlistrow, booksList);
                                    listView.setAdapter(adapter);
                                    progressBar.setVisibility(View.GONE);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(stringRequest);
    }
}
