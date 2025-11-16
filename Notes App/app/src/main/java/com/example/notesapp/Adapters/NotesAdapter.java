package com.example.notesapp.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.AddActivity;
import com.example.notesapp.MainActivity;
import com.example.notesapp.Models.Notes;
import com.example.notesapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesAdapter extends RecyclerView.Adapter<NotesViewHolder> {

    Context context;

    Activity activity;
    List<Notes> list;
    public NotesAdapter(Context context, List<Notes> list, Activity activity)
    {
        this.context = context;
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.row_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        int colorCode = getRandomColor();
        holder.container.setCardBackgroundColor(ContextCompat.getColor(context, colorCode));

        holder.title.setText(list.get(position).getTitle());
        holder.title.setSelected(true);

        holder.description.setText(list.get(position).getDescription());
        holder.date.setText(list.get(position).getDate());
        holder.date.setSelected(true);
        if (list.get(position).isPined()) {
            holder.pin.setImageResource(R.drawable.ic_pin);
        } else {
            holder.pin.setImageResource(0);
        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddActivity.class);
                intent.putExtra("old_note", list.get(holder.getAdapterPosition()));
                ((MainActivity) activity).startActivityForResult(intent, 201);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                PopupMenu popupMenu = new PopupMenu(context, holder.container);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = list.get(holder.getAdapterPosition()).getID();
                        if (menuItem.getItemId() == R.id.pin) {
                            if (list.get(holder.getAdapterPosition()).isPined()){
                                MainActivity.database.mainDAO().pin(id, false);
                                Toast.makeText(context, "The note is unpinned", Toast.LENGTH_SHORT).show();
                            } else {
                                MainActivity.database.mainDAO().pin(id, true);
                                Toast.makeText(context, "The note is pined", Toast.LENGTH_SHORT).show();
                            }
                            MainActivity.notes.clear();
                            MainActivity.notes.addAll(MainActivity.database.mainDAO().getAll());
                            notifyDataSetChanged();
                            return true;
                        } else if (menuItem.getItemId() == R.id.delete) {
                            MainActivity.database.mainDAO().destroy(list.get(holder.getAdapterPosition()));
                            MainActivity.notes.remove(list.get(holder.getAdapterPosition()));
                            notifyDataSetChanged();
                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                            return true;
                        } else {
                            return false;
                        }
                    }
                });
                popupMenu.inflate(R.menu.note_menu);
                popupMenu.show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    int getRandomColor() {
        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.color1);
        colorCode.add(R.color.color2);
        colorCode.add(R.color.color3);
        colorCode.add(R.color.color4);

        Random random = new Random();
        int randomColor = random.nextInt(colorCode.size());

        return colorCode.get(randomColor);
    }
}

class NotesViewHolder extends RecyclerView.ViewHolder{

    TextView title, description, date;
    ImageView pin;
    CardView container;
    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.tv_title);
        description = (TextView) itemView.findViewById(R.id.tv_description);
        date = (TextView) itemView.findViewById(R.id.tv_date);
        pin = (ImageView) itemView.findViewById(R.id.pin);
        container = (CardView) itemView.findViewById(R.id.cdv_container);


    }
}