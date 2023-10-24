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
    private SongAdapter songAdapter;
    private SongDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songListView = findViewById(R.id.songListView);
        songs = new ArrayList<>();
        songAdapter = new SongAdapter(this, songs); // Cambio Song a SongAdapter
        songListView.setAdapter(songAdapter);

        databaseHelper = new SongDatabaseHelper(this);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText titleEditText = findViewById(R.id.titleEditText);
                EditText authorEditText = findViewById(R.id.authorEditText);
                EditText youtubeUrlEditText = findViewById(R.id.youtubeUrlEditText);

                String title = titleEditText.getText().toString();
                String author = authorEditText.getText().toString();
                String youtubeUrl = youtubeUrlEditText.getText().toString();

                Song song = new Song(title, author, youtubeUrl);
                databaseHelper.addSong(song);

                titleEditText.setText("");
                authorEditText.setText("");
                youtubeUrlEditText.setText("");

                updateSongList(); 
            }
        });

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText titleEditText = findViewById(R.id.titleEditText);
                String title = titleEditText.getText().toString();
                Song song = new Song(title, "", "");
                databaseHelper.deleteSong(song);

                titleEditText.setText("");

                updateSongList();
            }
        });

        Button modifyButton = findViewById(R.id.modifyButton);
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText oldTitleEditText = findViewById(R.id.oldTitleEditText);
                EditText newTitleEditText = findViewById(R.id.newTitleEditText);
                EditText authorEditText = findViewById(R.id.authorEditText);
                EditText youtubeUrlEditText = findViewById(R.id.youtubeUrlEditText);

                String oldTitle = oldTitleEditText.getText().toString();
                String newTitle = newTitleEditText.getText().toString();
                String author = authorEditText.getText().toString();
                String youtubeUrl = youtubeUrlEditText.getText().toString();

                Song oldSong = new Song(oldTitle, "", "");
                Song newSong = new Song(newTitle, author, youtubeUrl);
                databaseHelper.modifySong(oldSong, newSong);

                oldTitleEditText.setText("");
                newTitleEditText.setText("");
                authorEditText.setText("");
                youtubeUrlEditText.setText("");

                updateSongList();
            }
        });
    }

    private void updateSongList() {
        songs.clear();
        songs.addAll(databaseHelper.getAllSongs());
        songAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateSongList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.close();
    }
}