package kevin.cox.thesmartshopper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by kevin on 20/02/2018.
 */

public class ItemDataBaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 11;

    // Database Name
    private static final String DATABASE_NAME = "shopItems";

    // items table name
    private static final String TABLE_ITEMS = "items";
    // scanned items table name
    private static final String TABLE_SCANNED = "scanned";

    // Contacts Table Columns names
    private static final String KEY_ID = "itemId";
    private static final String KEY_NAME = "itemName";
    private static final String KEY_PRICE = "itemPrice";
    private static final String KEY_IMAGE = "itemImageURL";

    public ItemDataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {


        //SQL for table creation
        String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PRICE + " DOUBLE," + KEY_IMAGE + " TEXT" + ")";
        Log.d("SQL CREATE DB",CREATE_ITEMS_TABLE);
        db.execSQL(CREATE_ITEMS_TABLE);
        String CREATE_SCANNED_TABLE = "CREATE TABLE " + TABLE_SCANNED + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PRICE + " DOUBLE," + KEY_IMAGE + " TEXT" + ")";
        Log.d("SQL CREATE DB",CREATE_SCANNED_TABLE);
        db.execSQL(CREATE_SCANNED_TABLE);

        try {
            populateFromJson(TABLE_ITEMS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCANNED);

        // Create tables again
        onCreate(db);

    }

    public void populateFromJson(String tableName) throws ExecutionException, InterruptedException {

        JSONPullThread thread = new JSONPullThread();
        ArrayList<ShopItem> items = thread.execute().get();
        for(ShopItem item:items){
            addItem(item,tableName);
        }
    }

    // Getting single item
    public ShopItem getItem(int id) {


        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ITEMS, new String[]{KEY_ID,
                        KEY_NAME, KEY_PRICE, KEY_IMAGE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor.moveToFirst()){
            ShopItem item = new ShopItem(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getDouble(2), cursor.getString(3));
            // return item
            return item;
        }
        return null;


    }

    public ArrayList<ShopItem> getAllItems(String tableName) {
        ArrayList<ShopItem> itemList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + tableName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ShopItem item = new ShopItem(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getDouble(2),cursor.getString(3));
                // Adding item to list
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        // return item list
        return itemList;
    }

    public void addItem(ShopItem item, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, item.getItemId()); // Item ID
        values.put(KEY_NAME, item.getItemName()); // Item Name
        values.put(KEY_PRICE, item.getItemPrice()); // Item Price
        values.put(KEY_IMAGE, item.getImageURL()); // Item Image URL

        // Inserting Row
        db.insert(tableName, null, values);
        // Closing database connection
        db.close();
    }

    public void removeItem(String itemId, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + tableName +" WHERE itemId = " + itemId);
    }
}