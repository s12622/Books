package pl.edu.pjwstk.s12622.books.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import pl.edu.pjwstk.s12622.books.Models.Book;
import pl.edu.pjwstk.s12622.books.R;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public BookAdapter(Context context, int resource, List<Book> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.itemlistrow, null);
        }

        Book p = getItem(position);

        if (p != null) {
            TextView title = (TextView) v.findViewById(R.id.titleText);
            TextView author = (TextView) v.findViewById(R.id.authorText);


            if (title != null) {
                title.setText(p.volumeInfo.title);
            }
            if (author != null) {
                if(p.volumeInfo.authors != null)
                author.setText(p.volumeInfo.authors.get(0));
                else author.setText("No authors");

            }


        }
        return v;
    }

}