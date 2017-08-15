package app.valeriachub.mustknowrecipes.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final String TAG = DatabaseHelper.class.getName();
    private static final String DATABASE_PATH = "/data/data/app.valeriachub.mustknowrecipes/databases";
    private static final String DATABASE_NAME = "DB_recipes.db";
    private static final int DATABASE_VERSION = 1;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public SQLiteDatabase createDb() {
        if (!context.getDatabasePath(DATABASE_PATH +"/"+ DATABASE_NAME).exists()) {
            try {
                copyDb();
                return openDb();
            } catch (IOException e) {
                Log.e(TAG, "Error copying a database");
                return null;
            }
        }
        return openDb();
    }

    private void copyDb() throws IOException {
        InputStream inputStream = context.getAssets().open(DATABASE_NAME);
        String localDbPath = DATABASE_PATH +"/"+ DATABASE_NAME;

        File file = new File(DATABASE_PATH);

        if (!file.exists()){
            file.mkdir();
        }

        OutputStream outputStream = new FileOutputStream(localDbPath);
        byte[] buffer = new byte[1024];
        int length;

        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    private SQLiteDatabase openDb() throws SQLException {
        String dbPath = DATABASE_PATH +"/"+ DATABASE_NAME;
        database = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
        return database;
    }

    @Override
    public synchronized void close() {
        if (database != null) {
            database.close();
        }
        super.close();
    }
}
