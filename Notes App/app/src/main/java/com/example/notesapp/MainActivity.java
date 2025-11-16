package com.example.notesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.notesapp.Adapters.NotesAdapter;
import com.example.notesapp.Database.RoomDB;
import com.example.notesapp.Models.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;

    FloatingActionButton fab;

    public static RoomDB database;
    public static List<Notes> notes = new ArrayList<>();
    NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_home);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        database = RoomDB.getInstance(MainActivity.this);
        notes = database.mainDAO().getAll();
        updateRecyclerView(notes);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, 200);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // تأكد من أن `requestCode` هو 200
        if (requestCode == 200) {
            // تحقق من أن النتيجة كانت ناجحة
            if (resultCode == Activity.RESULT_OK) {
                // احصل على الكائن Notes من الـ Intent
                Notes new_Note = (Notes) data.getSerializableExtra("note");

                // أدخل الملاحظة الجديدة في قاعدة البيانات
                database.mainDAO().insert(new_Note);

                // تحديث البيانات وعرضها في الـ RecyclerView أو الـ Adapter
                notes.clear();
                notes.addAll(database.mainDAO().getAll());
                notesAdapter.notifyDataSetChanged();
            }
        }
        if (requestCode == 201) {
            // تحقق من أن النتيجة كانت ناجحة
            if (resultCode == Activity.RESULT_OK) {
                // احصل على الكائن Notes من الـ Intent
                Notes old_Note = (Notes) data.getSerializableExtra("note");

                // أدخل الملاحظة الجديدة في قاعدة البيانات
                database.mainDAO().update(old_Note.getID(), old_Note.getTitle(), old_Note.getDescription());

                // تحديث البيانات وعرضها في الـ RecyclerView أو الـ Adapter
                notes.clear();
                notes.addAll(database.mainDAO().getAll());
                notesAdapter.notifyDataSetChanged();
            }
        }
    }


    private void updateRecyclerView(List<Notes> notes) {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);

        notesAdapter = new NotesAdapter(this, notes, MainActivity.this);
        recyclerView.setAdapter(notesAdapter);
    }


}