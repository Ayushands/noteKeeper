package com.practice.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    private NoteRecyclerAdapter mNoteRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteListActivity.this, NoteActivity.class));
            }
        });

        initializeDisplayContent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNoteRecyclerAdapter.notifyDataSetChanged();
    }

    private void initializeDisplayContent() {
        final RecyclerView recyclerNote = findViewById(R.id.list_notes);
        final LinearLayoutManager noteLayoutManager = new LinearLayoutManager(this);
        recyclerNote.setLayoutManager(noteLayoutManager);

        List<NoteInfo> notes = DataManager.getInstance().getNotes();
        mNoteRecyclerAdapter = new NoteRecyclerAdapter(this,notes);
        recyclerNote.setAdapter(mNoteRecyclerAdapter);
    }
}