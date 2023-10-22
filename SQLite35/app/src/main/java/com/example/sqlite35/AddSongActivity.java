package com.example.sqlite35;

import android.view.View;
import android.widget.Button;ppublic class AddSongActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText authorEditText;
    private EditText youtubeUrlEditText;
    private SongDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);

        titleEditText = findViewById(R.id.titleEditText);
        authorEditText = findViewById(R.id.authorEditText);
        youtubeUrlEditText = findViewById(R.id.youtubeUrlEditText);

        databaseHelper = new SongDatabaseHelper(this);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String author = authorEditText.getText().toString();
                String youtubeUrl = youtubeUrlEditText.getText().toString();
                Song song = new Song(title, author, youtubeUrl);
                databaseHelper.addSong(song);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.close();
    }
}