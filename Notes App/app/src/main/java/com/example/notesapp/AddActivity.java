package com.example.notesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.notesapp.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    EditText et_Title, et_Notes;
    ImageView save;
    Notes notes;

    boolean isOld = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);

        et_Title = findViewById(R.id.tv_insertTitle);
        et_Notes = findViewById(R.id.tv_insertDescription);
        save = findViewById(R.id.done_Iv);

        notes = new Notes();
        try {
            notes = (Notes) getIntent().getSerializableExtra("old_note");
            et_Title.setText(notes.getTitle());
            et_Notes.setText(notes.getDescription());
            isOld = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = et_Title.getText().toString();
                String desc = et_Notes.getText().toString();

                if (desc.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please insert your note", Toast.LENGTH_SHORT).show();
                    return;
                }

                SimpleDateFormat format = new SimpleDateFormat("EEE , d MMM yyyy HH:mm a");
                Date date = new Date();

                if (!isOld) {
                    notes = new Notes();
                }
                notes.setTitle(title);
                notes.setDescription(desc);
                notes.setDate(format.format(date));

                Intent intent = new Intent();
                intent.putExtra("note", notes);  // تمرير الكائن Notes عبر Intent
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
