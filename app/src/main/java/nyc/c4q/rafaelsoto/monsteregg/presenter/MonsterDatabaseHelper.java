package nyc.c4q.rafaelsoto.monsteregg.presenter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nyc.c4q.rafaelsoto.monsteregg.model.Monster;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by rook on 12/5/16.
 */

public class MonsterDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "monster.db";
    private static final int DATABASE_VERSION = 1;

    public MonsterDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    static {
        cupboard().register(Monster.class);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
