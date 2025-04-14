package com.example.motorentmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.motorentmobile.data.model.Rental;
import com.example.motorentmobile.databinding.ItemHistoryBinding;
import com.example.motorentmobile.view.HistoryDetailActivity;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<Rental> rentalList;
    private final Context context;

    public HistoryAdapter(Context context, List<Rental> rentalList) {
        this.context = context;
        this.rentalList = rentalList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemHistoryBinding binding = ItemHistoryBinding.inflate(inflater, parent, false);
        return new HistoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        Rental rental = rentalList.get(position);
        holder.binding.setRental(rental);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, HistoryDetailActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("RENTAL_DATA", rental);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return rentalList != null ? rentalList.size() : 0;
    }

    public void updateRentalList(List<Rental> newList) {
        this.rentalList = newList;
        notifyDataSetChanged();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        final ItemHistoryBinding binding;

        public HistoryViewHolder(ItemHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
