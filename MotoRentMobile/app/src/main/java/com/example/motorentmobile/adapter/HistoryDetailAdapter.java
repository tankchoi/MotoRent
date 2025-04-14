package com.example.motorentmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.motorentmobile.data.model.RentalDetail;
import com.example.motorentmobile.databinding.ItemHistoryDetailBinding;

import java.util.List;

public class HistoryDetailAdapter extends RecyclerView.Adapter<HistoryDetailAdapter.HistoryDetailViewHolder> {

    private final Context context;
    private  List<RentalDetail> rentalDetails;

    public HistoryDetailAdapter(Context context, List<RentalDetail> rentalDetails) {
        this.context = context;
        this.rentalDetails = rentalDetails;
    }
    @NonNull
    @Override
    public HistoryDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemHistoryDetailBinding binding = ItemHistoryDetailBinding.inflate(inflater, parent, false);
        return new HistoryDetailViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(HistoryDetailViewHolder holder, int position) {
        RentalDetail rentalDetail = rentalDetails.get(position);
        holder.binding.setRentalDetail(rentalDetail);
    }

    @Override
    public int getItemCount() {
        return rentalDetails != null ? rentalDetails.size() : 0; // Replace with actual item count
    }
    public void updateRentalDetailList(List<RentalDetail> newList) {
        this.rentalDetails = newList;
        notifyDataSetChanged();
    }
    public static class HistoryDetailViewHolder extends RecyclerView.ViewHolder {
        final ItemHistoryDetailBinding binding;
        public HistoryDetailViewHolder(ItemHistoryDetailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
