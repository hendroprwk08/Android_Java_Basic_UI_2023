package com.hendro.myapplication.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hendro.myapplication.databinding.RowItemBinding;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private final Context context;
    private final List<Presiden> list; // u/ menumpuk data, yang akan disimpan kedalam class Presiden

    public RecyclerViewAdapter(Context context, List<Presiden> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 1. binding - set view holder dengan binding
        RowItemBinding binding = RowItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecyclerViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RecyclerViewHolder holder, int position) {
        // 3. binding - masukkan nilai ke objek
        holder.binding.tvItemName.setText(list.get(position).getName());
        holder.binding.tvItemRemarks.setText(list.get(position).getRemarks());

        Glide.with(context)
                .load(list.get(position).getPhoto())
                .override(55,55)
                .into(holder.binding.imgItemPhoto);

        holder.itemView.setOnClickListener(v -> Toast.makeText(context, list.get(position).getName(), Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        // 2. binding - ItemRowPresident disandingkan dengan RecyclerViewHolder
        final RowItemBinding binding;

        public RecyclerViewHolder(@NonNull View itemView, RowItemBinding binding) {
            super(itemView);
            this.binding = binding;
        }
    }
}
