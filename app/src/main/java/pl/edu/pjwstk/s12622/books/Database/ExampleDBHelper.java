package pl.edu.pjwstk.s12622.books.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class ExampleDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "SQLiteExample.db";
    private static final int DATABASE_VERSION = 1;
    public static final String BOOK_TABLE_NAME = "books";
    public static final String BOOK_TITLE = "title";
    public static final String BOOK_ID = "_id";
    public static final String BOOK_PUBLISHER = "publisher";
    public static final String BOOK_DESCRIPTION = "description";
    public static final String BOOK_AUTHOR = "author";

    public ExampleDBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + BOOK_TABLE_NAME + "(" +
                BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BOOK_TITLE + " TEXT, " +
                BOOK_PUBLISHER + " TEXT, " +
                BOOK_AUTHOR + " TEXT, " +
                BOOK_DESCRIPTION + " TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + BOOK_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title, String author, String publisher, String description) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_TITLE, title);
        contentValues.put(BOOK_AUTHOR, author);
        contentValues.put(BOOK_PUBLISHER, publisher);
        contentValues.put(BOOK_DESCRIPTION, description);
        Log.d("Test", "-----------------Dane: " + title + "  " + author + "  " + publisher + "   " + description);
        db.insert(BOOK_TABLE_NAME, null, contentValues);
        Log.d("Test", "Dodano dane do bazy");
        return true;
    }

    public Cursor getAllBooks() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + BOOK_TABLE_NAME, null );
        return res;
    }

}
