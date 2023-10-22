package com.example.sqlite35;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "song_database";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "songs";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_YOUTUBE_URL = "youtube_url";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_AUTHOR + " TEXT, " +
                    COLUMN_YOUTUBE_URL + " TEXT)";

    public DataManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not implemented
    }

    public void addSong(Song song) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, song.getTitle());
        values.put(COLUMN_AUTHOR, song.getAuthor());
        values.put(COLUMN_YOUTUBE_URL, song.getYoutubeUrl());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                String author = cursor.getString(cursor.getColumnIndex(COLUMN_AUTHOR));
                String youtubeUrl = cursor.getString(cursor.getColumnIndex(COLUMN_YOUTUBE_URL));
                Song song = new Song(title, author, youtubeUrl);
                songs.add(song);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return songs;
    }

    public void deleteSong(Song song) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_TITLE + " = ?", new String[] { song.getTitle() });
        db.close();
    }

    public void modifySong(Song oldSong, Song newSong) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, newSong.getTitle());
        values.put(COLUMN_AUTHOR, newSong.getAuthor());
        values.put(COLUMN_YOUTUBE_URL, newSong.getYoutubeUrl());

        db.update(TABLE_NAME, values, COLUMN_TITLE + " = ?", new String[] { oldSong.getTitle() });
        db.close();
    }
}
}
