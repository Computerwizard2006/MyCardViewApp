package com.example.mycardview;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private final List<CardModel> list;

    public CardAdapter(List<CardModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardModel model = list.get(position);

        holder.title.setText(model.getTitle());
        holder.desc.setText(model.getDesc());
        
        // Directly setting image resource to ensure they show up reliably
        holder.image.setImageResource(model.getImage());

        holder.itemView.setOnClickListener(v -> animateAndShow(v, model));
        holder.button.setOnClickListener(v -> showDetailsDialog(v, model));
    }

    private void animateAndShow(View v, CardModel model) {
        v.animate()
                .scaleX(0.96f)
                .scaleY(0.96f)
                .setDuration(100)
                .withEndAction(() -> v.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(100)
                        .withEndAction(() -> showDetailsDialog(v, model)));
    }

    private void showDetailsDialog(View v, CardModel model) {
        new AlertDialog.Builder(v.getContext())
                .setTitle(model.getTitle())
                .setMessage("Full Details:\n\n" + model.getDesc() + "\n\nThis is an advanced professional learning module.")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, desc, extraText;
        ImageView image;
        MaterialButton button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleText);
            desc = itemView.findViewById(R.id.descText);
            image = itemView.findViewById(R.id.cardImage);
            button = itemView.findViewById(R.id.btnExplore);
            extraText = itemView.findViewById(R.id.extraText);
        }
    }
}
