package com.example.sqlite36;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SongAdapter extends ArrayAdapter<Song> {
    public SongAdapter(Context context, List<Song> songs) {
        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Song song = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_song, parent, false);
        }

        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView authorTextView = convertView.findViewById(R.id.authorTextView);
        TextView youtubeUrlTextView = convertView.findViewById(R.id.youtubeUrlTextView);

        titleTextView.setText(song.getTitle());
        authorTextView.setText(song.getAuthor());
        youtubeUrlTextView.setText(song.getYoutubeUrl());

        return convertView;
    }
}