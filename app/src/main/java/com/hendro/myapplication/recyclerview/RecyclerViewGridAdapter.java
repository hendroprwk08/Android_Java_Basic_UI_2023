package com.hendro.myapplication.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hendro.myapplication.databinding.GridItemBinding;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewGridAdapter extends RecyclerView.Adapter<RecyclerViewGridAdapter.RecyclerViewHolder>{
    private final Context context;
    private final List<Presiden> list;

    public RecyclerViewGridAdapter(Context context, List<Presiden> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerViewGridAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GridItemBinding binding = GridItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecyclerViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewGridAdapter.RecyclerViewHolder holder, int position) {
        // 3. binding - masukkan nilai ke objek
        holder.binding.tvItemName.setText(list.get(position).getName());

        Glide.with(context)
                .load(list.get(position).getPhoto())
                .into(holder.binding.imgItemPhoto);

        holder.itemView.setOnClickListener(v -> Toast.makeText(context, list.get(position).getName(), Toast.LENGTH_SHORT).show());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        final GridItemBinding binding;
        public RecyclerViewHolder(@NonNull View itemView, GridItemBinding binding) {
            super(itemView);
            this.binding = binding;
        }
    }
}
