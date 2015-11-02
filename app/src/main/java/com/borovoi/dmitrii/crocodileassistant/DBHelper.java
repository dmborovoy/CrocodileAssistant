package com.borovoi.dmitrii.crocodileassistant;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dimas on 11/2/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "wordsDB2";
    private static final String TABLE_WORDS = "words";
    private static final String WORDS_ID = "id";
    private static final String WORDS_WORD = "word";
    private static final String WORDS_IS_SINGLE = "single_word";
    private static final String WORDS_LEVEL = "level";
    private static final String LOG_TAG = "myLogs";

    private static final String[] COLUMNS = {WORDS_ID, WORDS_WORD, WORDS_IS_SINGLE, WORDS_LEVEL};

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WORDS_TABLE = "CREATE TABLE words (id INTEGER PRIMARY KEY AUTOINCREMENT, word TEXT, single_word BOOLEAN, level INTEGER(1))";
        db.execSQL(CREATE_WORDS_TABLE);
        db.execSQL(String.format("INSERT INTO %s (word, single_word, level) VALUES('%s', %s, %s)", TABLE_WORDS, "Стол", 0, 0));
        db.execSQL(String.format("INSERT INTO %s (word, single_word, level) VALUES('%s', %s, %s)", TABLE_WORDS, "Стул", 0, 0));
        db.execSQL(String.format("INSERT INTO %s (word, single_word, level) VALUES('%s', %s, %s)", TABLE_WORDS, "Пиджак", 0, 0));
        db.execSQL(String.format("INSERT INTO %s (word, single_word, level) VALUES('%s', %s, %s)", TABLE_WORDS, "Собака", 0, 0));
        db.execSQL(String.format("INSERT INTO %s (word, single_word, level) VALUES('%s', %s, %s)", TABLE_WORDS, "Дом", 0, 0));
        db.execSQL(String.format("INSERT INTO %s (word, single_word, level) VALUES('%s', %s, %s)", TABLE_WORDS, "Самолет", 0, 0));
        db.execSQL(String.format("INSERT INTO %s (word, single_word, level) VALUES('%s', %s, %s)", TABLE_WORDS, "Коррозия", 0, 1));
        db.execSQL(String.format("INSERT INTO %s (word, single_word, level) VALUES('%s', %s, %s)", TABLE_WORDS, "Туберкулез", 0, 1));
        db.execSQL(String.format("INSERT INTO %s (word, single_word, level) VALUES('%s', %s, %s)", TABLE_WORDS, "Гондурас", 0, 1));
        db.execSQL(String.format("INSERT INTO %s (word, single_word, level) VALUES('%s', %s, %s)", TABLE_WORDS, "Эмансипация", 0, 2));
        db.execSQL(String.format("INSERT INTO %s (word, single_word, level) VALUES('%s', %s, %s)", TABLE_WORDS, "Гипертрофия", 0, 2));
        db.execSQL(String.format("INSERT INTO %s (word, single_word, level) VALUES('%s', %s, %s)", TABLE_WORDS, "Большой стул", 1, 0));
        db.execSQL(String.format("INSERT INTO %s (word, single_word, level) VALUES('%s', %s, %s)", TABLE_WORDS, "Маленький стол", 1, 0));
        db.execSQL(String.format("INSERT INTO %s (word, single_word, level) VALUES('%s', %s, %s)", TABLE_WORDS, "Ромео и Джульета", 1, 1));
        db.execSQL(String.format("INSERT INTO %s (word, single_word, level) VALUES('%s', %s, %s)", TABLE_WORDS, "Тамбовский волк", 1, 1));
        db.execSQL(String.format("INSERT INTO %s (word, single_word, level) VALUES('%s', %s, %s)", TABLE_WORDS, "Был пацан и нет пацана", 1, 1));
        db.execSQL(String.format("INSERT INTO %s (word, single_word, level) VALUES('%s', %s, %s)", TABLE_WORDS, "Война и мир", 1, 1));
        db.execSQL(String.format("INSERT INTO %s (word, single_word, level) VALUES('%s', %s, %s)", TABLE_WORDS, "На Дерибасовской опять идут дожди", 1, 2));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public List<Word> getAllWordsRandom() {
        List<Word> words = new LinkedList<Word>();
        String query = "SELECT * FROM " + TABLE_WORDS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                words.add(map(cursor));
            } while (cursor.moveToNext());
        }
        return words;
    }

    public Word getRandom() {
        return getRandom(true, Level.LOW);
    }

    public Word getRandom(boolean single, Level level) {
        SQLiteDatabase db = this.getReadableDatabase();
        StringBuilder whereClause = new StringBuilder("WHERE " + WORDS_IS_SINGLE + "=");
        whereClause.append(single ? 0 : 1);
        whereClause.append(" AND ");
        whereClause.append(WORDS_LEVEL);
        whereClause.append("=");
        whereClause.append((level == null ? Level.LOW.getCode() : level.getCode()));
        Log.d(LOG_TAG, "where clause=" + whereClause);
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(TABLE_WORDS);
        query.append(" ");
        query.append(whereClause);
        query.append(" ORDER BY RANDOM() LIMIT 1");
        Log.d(LOG_TAG, "query clause=" + query);
        Cursor cursor = db.rawQuery(query.toString(), null);
        if (cursor != null && cursor.moveToFirst()) {
            return map(cursor);
        } else {
            return null;
        }
    }

    private Word map(Cursor cursor) {
        Word word = new Word();
        word.setId(cursor.getLong(0));
        word.setWord(cursor.getString(1));
        word.setIsSingleWord(cursor.getInt(2) > 0);
        word.setLevel(Level.findByCode(cursor.getInt(3)));
        return word;
    }
}
