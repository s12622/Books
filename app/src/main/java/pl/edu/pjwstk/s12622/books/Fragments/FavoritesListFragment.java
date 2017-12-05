package pl.edu.pjwstk.s12622.books.Fragments;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


import java.util.ArrayList;

import pl.edu.pjwstk.s12622.books.Database.ExampleDBHelper;
import pl.edu.pjwstk.s12622.books.Interfaces.FavoriteBookListener;
import pl.edu.pjwstk.s12622.books.R;

public class FavoritesListFragment extends ListFragment{
    private FavoriteBookListener favoriteBookListener;
    ExampleDBHelper dbHelper;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dbHelper = new ExampleDBHelper(getActivity().getApplicationContext());

        final Cursor cursor = dbHelper.getAllBooks();

        ArrayList<String> mArrayList = new ArrayList<String>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            mArrayList.add(cursor.getString(cursor.getColumnIndex("title"))); //add the item
            cursor.moveToNext();
        }

        setListAdapter(new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1,
                mArrayList));


        if(this.getActivity().findViewById(R.id.layout_default) == null)
        {
            favoriteBookListener.onFavoriteBookSelected(0);
        }

    }

    public FavoritesListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            favoriteBookListener = (FavoriteBookListener) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString() + " must implement the interface called FavoriteBookListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        favoriteBookListener.onFavoriteBookSelected(position);
    }
}
