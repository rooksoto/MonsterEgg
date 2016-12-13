package nyc.c4q.rafaelsoto.monsteregg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nyc.c4q.rafaelsoto.monsteregg.model.Monster;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by shannonalexander-navarro on 12/4/16.
 */

public class MonsterDatabaseHelper extends SQLiteOpenHelper{

        private static final String DATABASE_NAME = "monster.db";
        private static final int DATABASE_VERSION = 1;

        private static MonsterDatabaseHelper instance;

        public static synchronized MonsterDatabaseHelper getInstance(Context context) {

            if (instance == null) {
                instance = new MonsterDatabaseHelper(context.getApplicationContext());
            }
            return instance;
        }

        private MonsterDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        static {
            cupboard().register(Monster.class);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            cupboard().withDatabase(db).createTables();

            if (db == null){
            //add ids for monsters
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // this will upgrade tables, adding columns and new tables.
            // Note that existing columns will not be converted
            cupboard().withDatabase(db).upgradeTables();
            // do migration work if you have an alteration to make to your schema here
        }
    }


