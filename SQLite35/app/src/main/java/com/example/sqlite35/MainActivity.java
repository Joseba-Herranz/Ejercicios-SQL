package com.example.sqlite35;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView songListView;
    private List<Song> songs;
    private Song songAdapter;
    private DataManager databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songListView = findViewById(R.id.songListView);
        songs = new ArrayList<>();
        songAdapter = new Song(this, songs);
        songListView.setAdapter(songAdapter);

        databaseHelper = new DataManager(this);

        // TODO: Implement "Add a song" feature
        // TODO: Implement "Delete a song" feature
        // TODO: Implement "Modify song data" feature
    }

    @Override
    protected void onResume() {
        super.onResume();
        songs.clear();
        songs.addAll(databaseHelper.getAllSongs());
        songAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.close();
    }
}}