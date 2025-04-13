package com.example.motorentmobile.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.motorentmobile.data.api.ApiClient;
import com.example.motorentmobile.data.model.Vehicle;
import com.example.motorentmobile.databinding.ItemPaymentBinding;
import com.example.motorentmobile.databinding.ItemVehicleBinding;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.VehicleViewHolder> {

    private final List<Vehicle> vehicleList;

    public PaymentAdapter(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemPaymentBinding binding = ItemPaymentBinding.inflate(inflater, parent, false);
        return new VehicleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        Vehicle vehicle = vehicleList.get(position);
        holder.bind(vehicle);
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public static class VehicleViewHolder extends RecyclerView.ViewHolder {
        private final ItemPaymentBinding binding;

        public VehicleViewHolder(ItemPaymentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Vehicle vehicle) {
            binding.setVehicle(vehicle);
            binding.executePendingBindings();
            Glide.with(binding.getRoot().getContext())
                    .load(ApiClient.BASE_URL + vehicle.getVehicleImages().get(0).getUrl())
                    .into(binding.imageVehicle);
        }
    }
}
